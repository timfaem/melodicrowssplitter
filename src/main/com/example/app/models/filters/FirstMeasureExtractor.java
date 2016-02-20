package com.example.app.models.filters;

import com.example.app.models.Measure;
import com.example.app.models.Song;

public class FirstMeasureExtractor implements MusicalFeatureExtractor<Measure> {

    @Override
    public Measure extract(Song song) {
        return song.getMeasures() != null ? song.getMeasures().get(0) : new Measure.Builder().build();
    }

}
