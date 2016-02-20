package com.example.app.models.filters;

import com.example.app.models.Note;
import com.example.app.models.Song;

public final class FirstNoteExtractor implements MusicalFeatureExtractor<Note> {

    @Override
    public Note extract(Song song) {
        return new FirstMeasureExtractor().extract(song).getNotes().get(0);
    }
}
