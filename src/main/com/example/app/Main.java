package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
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
        List<Song> songs = TextToSongHelper.getSongs(new FileReader(path));

        System.out.println("Title           Year");
        SongHelper.sortByYear(songs);
        songs.forEach(s -> System.out.println(s.getTitle() + "     " + s.getYear()));
    }

}
