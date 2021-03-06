package com.example.app.models;

public enum Alter {

    NONE(0),
    FLAT(-1),
    SHARP(1);

    private final int alter;

    Alter(int alterType) {
        this.alter = alterType;
    }

    public static Alter fromInt(int alterType) {
        return alterType == -1 ? FLAT : alterType == 1 ? SHARP : NONE;
    }
}
