package main.models;

public class Pitch {

    private Alter alter = Alter.NONE;
    private Step step;
    private int octave;

    private Pitch() {
    }

    public static class Builder {
        private Pitch building = new Pitch();

        public Builder step(Step value) {
            building.step = value;
            return this;
        }

        public Builder octave(int value) {
            building.octave = value;
            return this;
        }

        public Builder alter(Alter value) {
            building.alter = value;
            return this;
        }

        public Pitch build() {
            return building;
        }
    }
}
