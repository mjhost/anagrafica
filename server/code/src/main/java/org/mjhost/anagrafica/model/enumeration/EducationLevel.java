package org.mjhost.anagrafica.model.enumeration;

public enum EducationLevel {

    PRIMARY_SCHOOL("Scuola elementare"),

    MIDDLE_SCHOOL("Scuola media"),

    HIGH_SCHOOL("Scuola superiore"),

    BACHELOR_DEGREE("Laurea triennale"),

    MASTER_DEGREE("Laurea magistrale"),

    PHD("Dottorato di ricerca");

    private String value;

    EducationLevel(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}