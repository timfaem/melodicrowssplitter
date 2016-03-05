package com.example.app.models.filters;

import com.example.app.models.Note;
import com.example.app.models.Song;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FirstMelodicRowExtractor implements MusicalFeatureExtractor<List<Note>> {

    @Override
    public List<Note> extract(Song song) {

        String melRowNoteNumber = song.getMelodicRows().trim().split(",")[0];
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(melRowNoteNumber);
        Integer noNotesFirstMelRow = 0;
        while (matcher.find()) {
            noNotesFirstMelRow = Integer.parseInt(matcher.group(1));
        }
        return song.flattenSong().stream().limit(noNotesFirstMelRow).collect(Collectors.toList());
    }

}
