package main.models;

public class Note {
    private int duration;
    private Pitch pitch;

    private Note() {
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
}
