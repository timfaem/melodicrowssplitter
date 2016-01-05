package main.models;

import java.util.List;

public class Song {

    private final String path;
    private final int year;
    private final Location location;
    private String melodicRows;
    private List<Measure> measures;
    private Genre genre;

    public Song(String path, int year, Location location, Genre genre) {
        this.path = path;
        this.year = year;
        this.location = location;
        this.genre = genre;
    }

    public Song(String path, Location location) {
        this(path, 0, location, null);
    }

    public String getPath() {
        return path;
    }
}
