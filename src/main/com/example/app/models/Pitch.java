package com.example.app.models;

public class Pitch implements Comparable<Pitch>{

    private Alter alter = Alter.NONE;
    private Step step;
    private int octave;

    private Pitch() {
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "alter=" + alter +
                ", step=" + step +
                ", octave=" + octave +
                '}';
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

    public Alter getAlter() {
        return alter;
    }

    public Step getStep() {
        return step;
    }

    public int getOctave() {
        return octave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pitch pitch = (Pitch) o;

        if (octave != pitch.octave) return false;
        if (alter != pitch.alter) return false;
        return step == pitch.step;
    }

    @Override
    public int hashCode() {
        int result = alter != null ? alter.hashCode() : 0;
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + octave;
        return result;
    }

    /**
     * @param pitch
     * @return 0 if equal
     * 1 if this is above than pitch
     * -1 if this is under than pitch
     */
    @Override
    public int compareTo(Pitch pitch) {
        if (octave == pitch.getOctave() && step == pitch.getStep() && alter == pitch.getAlter()) {
            return 0;
        }
        if (octave < pitch.getOctave())
            return -1;
        if (octave > pitch.getOctave())
            return 1;

        // same octave
        if (step.compareTo(pitch.getStep()) < 0) {
            return -1;
        }
        if (step.compareTo(pitch.getStep()) > 0) {
            return 1;
        }

        //octave and step are the same
        if (alter.compareTo(pitch.getAlter()) < 0) {
            return -1;
        }
        if (alter.compareTo(pitch.getAlter()) > 0) {
            return 1;
        }
        return 0;
    }
}
