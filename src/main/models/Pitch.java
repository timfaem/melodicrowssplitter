package main.models;

public class Pitch {

    private final Step step;
    private final int octave;

    public Pitch(Step step, int octave) {
        this.step = step;
        this.octave = octave;
    }

    public Step getStep() {
        return step;
    }

    public int getOctave() {
        return octave;
    }
}
