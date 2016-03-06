package com.example.app.models;

public class Note implements Comparable<Note> {
    private int duration;
    private Pitch pitch;

    private Note() {
    }

    @Override
    public String toString() {
        return "Note{" +
                "duration=" + duration +
                ", pitch=" + pitch +
                '}';
    }

    @Override
    public int compareTo(Note o) {
        return pitch.compareTo(o.getPitch());
    }

    public static class Builder {
        private Note building = new Note();

        public Builder duration(int value) {
            building.duration = value;
            return this;
        }

        public Builder pitch(Pitch value) {
            building.pitch = value;
            return this;
        }

        public Note build() {
            return building;
        }
    }

    public int getDuration() {
        return duration;
    }

    public Pitch getPitch() {
        return pitch;
    }
}
