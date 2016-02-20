package com.example.app.models;

import java.util.HashMap;
import java.util.Map;

public enum Genre {

    BALADA("Bal"),
    BOCET("Boc"),
    BRADULUI("Bra"),
    CATANIE("Cat"),
    COLINDA("Col"),
    C_PROP_ZIS("Cpr"),
    C_VOCAL_JOC("Cvj"),
    DOINA("Doi"),
    FOLCLORUL_COPIILOR("Fco"),
    FUNEBRU("Fun"),
    LEAGAN("Leg"),
    LIOARA("Lio"),
    MIORITA("Mio"),
    MIREASA("Mir"),
    PAPARUDA("Pap"),
    SECERIS("Sec"),
    SEZATOARE("Sez"),
    VERGEL("Ver"),
    ZORILOR("Zor");

    private String abbrev;
    private static Map<String, Genre> map = new HashMap<>();

    static {
        map.put(BALADA.abbrev, BALADA);
        map.put(BOCET.abbrev, BOCET);
        map.put(BRADULUI.abbrev, BRADULUI);
        map.put(CATANIE.abbrev, CATANIE);
        map.put(COLINDA.abbrev, COLINDA);
        map.put(C_PROP_ZIS.abbrev, C_PROP_ZIS);
        map.put(C_VOCAL_JOC.abbrev, C_VOCAL_JOC);
        map.put(DOINA.abbrev, DOINA);
        map.put(FOLCLORUL_COPIILOR.abbrev, FOLCLORUL_COPIILOR);
        map.put(FUNEBRU.abbrev, FUNEBRU);
        map.put(LEAGAN.abbrev, LEAGAN);
        map.put(LIOARA.abbrev, LIOARA);
        map.put(MIORITA.abbrev, MIORITA);
        map.put(MIREASA.abbrev, MIREASA);
        map.put(PAPARUDA.abbrev, PAPARUDA);
        map.put(SECERIS.abbrev, SECERIS);
        map.put(SEZATOARE.abbrev, SEZATOARE);
        map.put(VERGEL.abbrev, VERGEL);
        map.put(ZORILOR.abbrev, ZORILOR);
    }

    public static Genre fromString(String string) {
        return map.get(string);
    }

    Genre(String abbrev) {
        this.abbrev = abbrev;
    }
}
