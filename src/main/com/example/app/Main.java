package com.example.app;

import com.example.app.filehelpers.SongFileReader;
import com.example.app.filehelpers.XMLtoSong;
import com.example.app.models.Song;
import com.example.app.models.helpers.SongHelper;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String path = System.getProperty("user.dir") + "\\input\\test";
        SongFileReader sfReader = new SongFileReader(path);
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

        System.out.println("Title           Year");
        SongHelper.sortByYear(songs);
        songs.forEach(s -> System.out.println(s.getTitle() + "     " + s.getYear()));
    }
}
