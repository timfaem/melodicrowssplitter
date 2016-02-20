package com.example.app.filehelpers;

import com.example.app.models.Song;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TextToSongHelper {

    public static List<Song> getSongs(FileReader sfReader) {
        int ignored = 0;
        List<Song> songs = new ArrayList<>();
        while (sfReader.hasNext()) {
            sfReader.advance();
            String xmlString = sfReader.getFileText();
            if (xmlString == null) {
                continue;
            }

            try {
                Song s = XMLtoSong.toSong(xmlString, sfReader.getFileTitle());
                songs.add(s);
            } catch (JSONException ex) {
                ignored++;
                continue;
            }
        }
        System.out.println("Number of ignored files: " + ignored);
        return songs;
    }

}
