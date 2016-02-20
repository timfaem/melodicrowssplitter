package com.example.app.models.filters;

import com.example.app.models.Song;

public interface MusicalFeatureExtractor<FEATURE> {

    FEATURE extract(Song song);

}
