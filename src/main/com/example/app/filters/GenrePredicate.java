package com.example.app.filters;

import com.example.app.models.Genre;
import com.example.app.models.Song;

import java.util.function.Predicate;

public class GenrePredicate implements Predicate<Song> {

    private final Genre genre;

    public GenrePredicate(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean test(Song song) {
        return song.getGenre() == genre;
    }
}
