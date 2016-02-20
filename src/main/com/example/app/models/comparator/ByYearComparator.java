package com.example.app.models.comparator;

import com.example.app.models.Song;


public class ByYearComparator implements SongComparator {

    @Override
    public int compare(Song o1, Song o2) {
        return o1.getYear() > o2.getYear() ?
                1 : o1.getYear() == o2.getYear() ?
                0 : -1;
    }
}
