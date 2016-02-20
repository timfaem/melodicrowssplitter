package com.example.app.models.comparator;

import com.example.app.models.Song;

public class ByTitleComparator implements SongComparator {

    @Override
    public int compare(Song o1, Song o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
