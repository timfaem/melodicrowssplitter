package main.files;

import main.models.Song;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongFileReader {

    private String inputFolderPath;
    private DocumentBuilder cachedDocBuilder;
    List<Song> songs = new ArrayList<>();

    public SongFileReader(String inputFolderPath) {
        this.inputFolderPath = inputFolderPath;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            factory.setFeature("http://xml.org/sax/features/namespaces", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            cachedDocBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Song> readSongs(String inputFolderPath) throws IOException, SAXException {
        for (String songFilePath : getFilePaths(inputFolderPath)) {
            Document doc = getDocumentForFileAtPath(songFilePath);
            Song song = XMLtoSongHelper.readSong(doc);
//            songs.add(song);s
        }
        return songs;
    }

    public List<String> getFilePaths(String folderPath) {
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

    public Document getDocumentForFileAtPath(String filePath) throws IOException, SAXException {
        return cachedDocBuilder.parse(new File(filePath));
    }

    public String getCreatorFromDocument(String tagName, Document document) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            String creatorData = nodeList.item(i).getFirstChild().getNodeValue();
            String[] split = creatorData.split("\n");
            if (split.length > 2) {
                return split[2].substring(0, split[2].length() - 1);
            }
        }
        return null;
    }
}
