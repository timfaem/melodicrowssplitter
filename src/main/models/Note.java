package main.models;

public class Note {
    private final int duration;
    private final Pitch pitch;
    private final Alter alter;

    public Note(int duration, Pitch pitch, Alter alter) {
        this.duration = duration;
        this.pitch = pitch;
        this.alter = alter;
    }

    public int getDuration() {
        return duration;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Alter getAlter() {
        return alter;
    }
}
