package com.example.app.models.helpers;

import com.example.app.models.Song;
import com.example.app.models.comparator.ByGenreComparator;
import com.example.app.models.comparator.ByTitleComparator;
import com.example.app.models.comparator.ByYearComparator;

import java.util.List;

public final class SongHelper {

    private SongHelper() {
    }

    public static List<Song> sortByYear(final List<Song> songs) {
        songs.sort((o1, o2) -> new ByYearComparator().compare(o1, o2));
        return songs;
    }


    public static List<Song> sortByTitle(final List<Song> songs) {
        songs.sort((o1, o2) -> new ByTitleComparator().compare(o1, o2));
        return songs;
    }

    public static List<Song> sortByGenre(final List<Song> songs) {
        songs.sort((o1, o2) -> new ByGenreComparator().compare(o1, o2));
        return songs;
    }
}
