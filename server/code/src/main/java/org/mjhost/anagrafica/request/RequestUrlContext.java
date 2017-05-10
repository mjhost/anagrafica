package org.mjhost.anagrafica.request;

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
