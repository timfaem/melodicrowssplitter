package com.example.app.models.filters;

import com.example.app.models.Note;
import com.example.app.models.Song;

import java.util.List;

public class LastNoteExtractor implements MusicalFeatureExtractor<Note> {

    private final FirstMelodicRowExtractor melRowExtr = new FirstMelodicRowExtractor();

    @Override
    public Note extract(Song song) {
        List<Note> firstrow = melRowExtr.extract(song);
        return firstrow.get(firstrow.size() - 1);
    }
}
