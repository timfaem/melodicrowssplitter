package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.filters.GenrePredicate;
import com.example.app.models.*;
import com.example.app.models.filters.FirstNoteExtractor;
import com.example.app.models.helpers.SongHelper;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String path = System.getProperty("user.dir") + "\\input\\all";
        List<Song> songs = TextToSongHelper.getSongs(new FileReader(path));

//        System.out.println("Title           Year");
//        SongHelper.sortByYear(songs);
//        List<Song> knownYearSongs = SongHelper.trimNonYear(songs);
//        knownYearSongs.forEach(s -> {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(s.getTitle() + "     " + s.getYear() + "    " + s.getLocation());
//        });

        

        GenrePredicate predicate = new GenrePredicate(Genre.C_PROP_ZIS);
        List<Song> filteredSongs = SongHelper.trimNonYear(SongHelper.sortByYear(SongHelper.filterSongs(songs, predicate)));
        filteredSongs.forEach( s -> System.out.println(s.getTitle() + "     " + s.getYear() + "    " + s.getGenre()));

//        printHistogram(createHistogram(songs));
//        whatever(songs);
    }

    private void printHistogram(Map<Step, AtomicInteger> histogram) {
        for (Map.Entry<Step, AtomicInteger> entry : histogram.entrySet()) {
            System.out.println(entry.getKey() + "  " + prettyPrint(entry.getValue()));
        }
    }

    private Map<Step, AtomicInteger> createHistogram(List<Song> songs) {
        Map<Step, AtomicInteger> histogram = new HashMap<>();
        for (Step s : Step.values()) {
            histogram.put(s, new AtomicInteger(0));
        }

        songs.forEach(s -> {
            Note n = new FirstNoteExtractor().extract(s);
            histogram.get(n.getPitch().getStep()).incrementAndGet();
        });
        return histogram;
    }

    private void whatever(List<Song> songs) {
        Song first = songs.get(0);
        System.out.println(first.toString());
        JSONObject firstJson = new JSONObject(first);
        System.out.println(firstJson);

        try {
            FileWriter writer = new FileWriter("song_json_test.txt");
            writer.write(String.valueOf(firstJson));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String prettyPrint(AtomicInteger value) {
        int charactedToPrint = value.intValue() / 50;
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < charactedToPrint; i++) {
            sbuilder.append("**");
        }
        return sbuilder.toString();
    }

}
