package org.mjhost.anagrafica.model.enumeration;

public enum EducationLevel {

    PRIMARY_SCHOOL("Primary school"),

    MIDDLE_SCHOOL("Middle school"),

    HIGH_SCHOOL("High school"),

    BACHELOR_DEGREE("Bachelor degree"),

    MASTER_DEGREE("Master degree"),

    PHD("Ph.D.");

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