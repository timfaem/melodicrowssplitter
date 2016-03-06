package com.example.app.models.filters;

import com.example.app.models.Measure;
import com.example.app.models.Note;
import com.example.app.models.Song;

public class LastNoteExtractor implements MusicalFeatureExtractor<Note> {

    @Override
    public Note extract(Song song) {
        Measure measure = song.getMeasures().get(song.getMeasures().size() - 1);
        return measure.getNotes().get(measure.getNotes().size() - 1);
    }

}
