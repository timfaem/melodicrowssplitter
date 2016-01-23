package com.example.main.models;

import java.util.HashMap;
import java.util.Map;

public enum Step {

    A, B, C, D, E, F, G;

    private static Map<String, Step> values = new HashMap<String, Step>();

    static {
        values.put("A", A);
        values.put("B", B);
        values.put("C", C);
        values.put("D", D);
        values.put("E", E);
        values.put("F", F);
        values.put("G", G);
    }

    public static Step fromString(String str) {
        return values.get(str);
    }
}
