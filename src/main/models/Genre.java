package main.models;

import java.util.HashMap;
import java.util.Map;

public enum Genre {

    BALADA("Bal"),
    BOCET("Boc"),
    BRADULUI("Bra"),
    CATANIE("Cat"),
    COLINDA("Col"),
    C_PROP_ZIS("Cpr"),
    MIORITA("Mio");

    private String abbrev;
    private static Map<String, Genre> map = new HashMap<>();

    static {
        map.put(BALADA.abbrev, BALADA);
        map.put(BOCET.abbrev, BOCET);
        map.put(BRADULUI.abbrev, BRADULUI);
        map.put(CATANIE.abbrev, CATANIE);
        map.put(COLINDA.abbrev, COLINDA);
        map.put(C_PROP_ZIS.abbrev, C_PROP_ZIS);
        map.put(MIORITA.abbrev, MIORITA);
    }

    public static Genre fromString(String string) {
        return map.get(string);
    }

    Genre(String abbrev) {
        this.abbrev = abbrev;
    }
}
