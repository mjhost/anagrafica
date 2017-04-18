package org.mjhost.anagrafica.model.enumeration;

public enum ContactType {

    HOME("casa"),

    WORK("lavoro");

    private String value;

    ContactType(String value) {
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
