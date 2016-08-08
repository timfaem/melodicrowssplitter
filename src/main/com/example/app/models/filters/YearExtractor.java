package com.example.app.models.filters;

import com.example.app.models.Song;

public class YearExtractor implements MusicalFeatureExtractor<Integer> {

    @Override
    public Integer extract(Song song) {
        return song.getYear();
    }

}
