package com.example.app.filehelpers;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private String inputFolderPath;
    private List<String> songPaths = new ArrayList<>();
    private int currentIndex = -1;


    public FileReader(String inputFolderPath) {
        this.inputFolderPath = inputFolderPath;
        this.songPaths = getFilePaths(inputFolderPath);
    }

    public String getFileText() {
        try {
            return readFileText(songPaths.get(currentIndex));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void advance() {
        currentIndex++;
    }

    public boolean hasNext() {
        return currentIndex < songPaths.size() - 1;
    }

    public String getFileTitle() {
        return songPaths.get(currentIndex);
    }

    private String readFileText(String songFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(songFilePath), Charset.forName("utf-16")));

        String line = reader.readLine();
        if (!line.startsWith("<?xml"))
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(songFilePath), Charset.forName("utf-8")));
            //TODO check if the ignore of first line is needed
            line = reader.readLine();
        }
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append(System.lineSeparator());
        }
        return builder.toString();
    }

    private List<String> getFilePaths(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        System.out.println("Files at: " + folderPath);
        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Filter for input files: ", ".xml");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {// && extFilter.accept(listOfFiles[i])) {
                fileNames.add(inputFolderPath + "\\" + listOfFiles[i].getName());
            }
        }
        return fileNames;
    }

    public int getTotalSongsNumber()
    {
        return songPaths.size();
    }
}
