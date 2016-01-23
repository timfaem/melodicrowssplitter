package com.example.main;

import com.example.main.filehelpers.SongFileReader;
import com.example.main.filehelpers.XMLtoSong;
import com.example.main.models.Song;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String path = System.getProperty("user.dir") + "\\input\\test";
        SongFileReader sfReader = new SongFileReader(path);

        List<Song> songs = new ArrayList<>();
        while (sfReader.hasNext()) {
            sfReader.advance();
            String xmlString = sfReader.getFileText();
            if (xmlString != null) {
                Song s = XMLtoSong.toSong(xmlString);
                songs.add(s);
                System.out.println(s);
            }
        }
    }
}
