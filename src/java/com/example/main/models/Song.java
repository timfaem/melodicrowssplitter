package com.example.main.models;

import java.util.ArrayList;
import java.util.List;

public class Song {

    private String author;
    private String title;
    private int year;
    private Location location;
    private String melodicRows;
    private List<Measure> measures = new ArrayList<>();
    private Genre genre;

    private Song() {
    }

    @Override
    public String toString() {
        return "Song{" + "\n" +
                "title='" + title + "\n" +
                ", year=" + year + "\n" +
                ", location=" + location + "\n" +
                ", melodicRows='" + melodicRows + "\n" +
                ", genre=" + genre + "\n" +
                ", author=" + author + "\n" +
                ", measures=" + measures +
                '}';
    }

    public static class Builder {

        private Song building = new Song();

        public Builder author(String value) {
            building.author = value;
            return this;
        }

        public Builder title(String value) {
            building.title = value;
            return this;
        }

        public Builder year(int value) {
            building.year = value;
            return this;
        }

        public Builder location(Location value) {
            building.location = value;
            return this;
        }

        public Builder melodicRows(String value) {
            building.melodicRows = value;
            return this;
        }

        public Builder addMeasure(Measure value) {
            building.measures.add(value);
            return this;
        }

        public Builder measures(List<Measure> value) {
            building.measures = value;
            return this;
        }

        public Builder genre(Genre value) {
            building.genre = value;
            return this;
        }

        public Song build() {
            return building;
        }

    }
}
