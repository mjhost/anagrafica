package org.mjhost.anagrafica.enumeration;

public enum RequestUrlContext {

    PEOPLE("people"),

    SYSTEM("system");

    private String value;

    RequestUrlContext(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
