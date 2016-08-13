package com.example.app;

import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.filehelpers.XMLtoSong;
import com.example.app.histograms.YearHistogram;
import com.example.app.models.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.app.filehelpers.MapGenerator.generateMapFile;

public class Main {

    private static final String PATH_ALL = System.getProperty("user.dir") + "\\input\\all";
    private static final String PATH_TEST = System.getProperty("user.dir") + "\\input\\test";
    private static final String PATH_EVERYONE = "C:\\Users\\Tm\\Desktop\\judi doctorat\\RFSDB xml";
    private static final String ARRANGED_RESULT_COMPARE_6_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_6_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_6_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_6_Timp_.csv";
    private static final String ARRANGED_RESULT_COMPARE_7_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_7_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_7_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_7_Timp_.csv";

    private static final String ARRANGED_RESULT_COMPARE_8_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_8_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_8_NOTE_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_8_Note_Timp_.csv";

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {

        List<Song> songs = TextToSongHelper.getSongs(PATH_EVERYONE);
        System.out.println("Total songs processed: " + songs.size());
        System.out.println(XMLtoSong.exceptions);

        YearHistogram allSongs = new YearHistogram(songs, "all");
        allSongs.print();

        Map<String, Song> songTitleMap = new HashMap<>();
        Map<String, Song> songFileNameMap = new HashMap<>();
        for (Song s : songs) {
            songTitleMap.put(s.getTitle(), s);
            songFileNameMap.put(s.getFileName(), s);
        }

        File file = new File(ARRANGED_RESULT_COMPARE_6_NOTE);
        try {
            BufferedReader bRead = new BufferedReader(new java.io.FileReader(file));
            bRead.readLine();
            String line = bRead.readLine(); //ignore column titles
//            while ((line = bRead.readLine()) != null) {
            String idName = file.getName() + "-" + line.split("\\s*\\s")[0];
            List<Song> samePatternSongs = extractSongInfo(songTitleMap, songFileNameMap, line);

            YearHistogram gatherYearHisto = new YearHistogram(samePatternSongs, idName);
            gatherYearHisto.print();
            generateMapFile(samePatternSongs, idName);

//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Song> extractSongInfo(Map<String, Song> songTitleMap, Map<String, Song> songFileNameMap, String line) {
        String[] parts = line.split(",");
        Integer count = Integer.parseInt(parts[0]);
        String pattern = parts[1];

        List<Song> selectedSongs = new ArrayList<>();
        for (int i = 2; i < count + 2; i++) {
            String songFileName = trimFileName(parts[i]);
            Song song = songFileNameMap.get(songFileName);
            if (song == null) {
                songFileName = songFileName.replace(".xml", "");
                song = songTitleMap.get(songFileName);
                if (song != null) { // if found by song name
                    selectedSongs.add(song);
                }
            } else {  // if found by file name
                selectedSongs.add(song);
            }
        }

        computeStdDev(count, pattern, selectedSongs);
        return selectedSongs;
    }

    private Double computeStdDev(Integer count, String pattern, List<Song> selectedSongs) {
        int sum = 0;
        int totalNoNullYearSongs = 0;
        for (Song s : selectedSongs) {
            if (s.getYear() != 0) {
                totalNoNullYearSongs++;
                sum += s.getYear();
            }
        }
        Double avg = 0.0;
        if (totalNoNullYearSongs != 0) {
            avg = Double.valueOf(sum / totalNoNullYearSongs);
        }

        Double varianceNumerator = 0.0;
        for (Song s : selectedSongs) {
            varianceNumerator += Math.pow(s.getYear() - avg, 2);
        }
        Double stdDev = totalNoNullYearSongs != 0 ? Math.sqrt(varianceNumerator / totalNoNullYearSongs) : 9999;
        System.out.println("Count: " + count + " Nr: " + totalNoNullYearSongs + " Pattern: " + pattern + " StdDev: " + stdDev);
        return stdDev;
    }

    private String trimFileName(String songFileName) {
        if (songFileName.startsWith("[")) {
            songFileName = songFileName.substring(1);
        }
        if (songFileName.endsWith("]")) {
            songFileName = songFileName.substring(0, songFileName.length() - 1);
        }
        if (songFileName.startsWith(" ")) {
            songFileName = songFileName.substring(1);
        }
        songFileName = songFileName.replace(".txt", ".xml");
        return songFileName;
    }
}
