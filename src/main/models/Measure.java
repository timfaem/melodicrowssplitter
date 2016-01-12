package main.models;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private List<Note> notes = new ArrayList<>();

    private Measure() {
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
