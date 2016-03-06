
package com.example.app.models;

import java.util.HashMap;
import java.util.Map;

public enum Step {

    C, D, E, F, G, A, B;

    private static Map<String, Step> values = new HashMap<String, Step>();

    static {
        values.put("C", C);
        values.put("D", D);
        values.put("E", E);
        values.put("F", F);
        values.put("G", G);
        values.put("A", A);
        values.put("B", B);
    }

    public static Step fromString(String str) {
        return values.get(str);
    }

}
