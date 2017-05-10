package org.mjhost.anagrafica.enumeration;

public enum RequestHandler {

    FIND_PEOPLE("findPeople");

    private String value;

    RequestHandler(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
