package main.models;

public enum Genre {

    BALADA("Bal"),
    BOCET("Boc"),
    BRADULUI("Bra"),
    CATANIE("Cat"),
    COLINDA("Col"),
    C_PROP_ZIS("Cpr"),
    MIORITA("Mio");

    private String abbrev;

    Genre(String abbrev)
    {
        this.abbrev = abbrev;
    }
}
