package com.example.app.models.comparator;

import com.example.app.models.Song;

public class ByGenreComparator implements SongComparator {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getGenre().getAbbrev().compareTo(o2.getGenre().getAbbrev());
    }
}
