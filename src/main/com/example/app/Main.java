package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.models.NormalizedStep;
import com.example.app.models.Note;
import com.example.app.models.Song;
import com.example.app.models.filters.FirstNoteExtractor;
import com.example.app.models.helpers.SongHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String path = System.getProperty("user.dir") + "\\input\\all";
        List<Song> songs = TextToSongHelper.getSongs(new FileReader(path));

        System.out.println("Title           Year");
        SongHelper.sortByYear(songs);
        songs.forEach(s -> System.out.println(s.getTitle() + "     " + s.getYear()));


        System.out.println("\n\n\n\n");
        Map<NormalizedStep, AtomicInteger> histogram = new HashMap<>();
        for (NormalizedStep s : NormalizedStep.values()) {
            histogram.put(s, new AtomicInteger(0));
        }

        songs.forEach(s -> {
            Note n = new FirstNoteExtractor().extract(s);
            NormalizedStep nStep = NormalizedStep.getNormalizedStepFrom(n.getPitch().getStep(), n.getPitch().getAlter());
            histogram.get(nStep).incrementAndGet();
        });

        for (Map.Entry<NormalizedStep, AtomicInteger> entry : histogram.entrySet()) {
            System.out.println(
                    entry.getKey().toString().length() > 5 ?
                            entry.getKey() + "   " + prettyPrint(entry.getValue()) :
                            entry.getKey() + "        " + prettyPrint(entry.getValue()));

        }
    }

    private String prettyPrint(AtomicInteger value) {
        int charactedToPrint = value.intValue() / 50;
        StringBuilder sbuier = new StringBuilder();
        for (int i = 0; i < charactedToPrint; i++) {
            sbuier.append("**");
        }
        return sbuier.toString();
    }

}
