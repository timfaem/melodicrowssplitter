package com.example.main;

import com.example.main.filehelpers.SongFileReader;
import com.example.main.filehelpers.XMLtoSong;
import com.example.main.models.Song;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String path = System.getProperty("user.dir") + "\\input\\all";
        SongFileReader sfReader = new SongFileReader(path);

        List<Song> songs = new ArrayList<>();
        while (sfReader.hasNext()) {
            sfReader.advance();
            String xmlString = sfReader.getFileText();
            if (xmlString != null) {
                try {
                    Song s = XMLtoSong.toSong(xmlString, sfReader.getFileTitle());
                    songs.add(s);
                } catch (JSONException ex) {
                    System.out.printf("EXCEPTION");
                    break;
                }
//                System.out.println(s);
            }
        }
    }
}
