package com.example.app.models;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private List<Note> notes = new ArrayList<>();

    private Measure() {
    }

    @Override
    public String toString() {
        return "Measure{" +
                "notes=" + notes +
                '}';
    }

    public static class Builder {

        private Measure building = new Measure();

        public Builder addNote(Note value) {
            building.notes.add(value);
            return this;
        }

        public Builder notes(List<Note> value) {
            building.notes = value;
            return this;
        }

        public Measure build() {
            return building;
        }

    }


}
