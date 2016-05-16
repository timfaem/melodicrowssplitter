package com.example.app.filehelpers;

import com.example.app.models.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextToSongHelper {

    public static List<Song> getSongs(FileReader sfReader) throws IOException {
        int ignored = 0;
        List<Song> songs = new ArrayList<>();
        LocationFileReader locFR = new LocationFileReader();
        while (sfReader.hasNext()) {
            sfReader.advance();
            String xmlString = sfReader.getFileText();
            if (xmlString == null) {
                continue;
            }

            try {
                Song s = XMLtoSong.toSong(xmlString, sfReader.getFileTitle(), locFR);
                songs.add(s);
            } catch (Exception ex) {
                ignored++;
                continue;
            }
        }
        System.out.println("Number of ignored files: " + ignored);
        return songs;
    }

}
