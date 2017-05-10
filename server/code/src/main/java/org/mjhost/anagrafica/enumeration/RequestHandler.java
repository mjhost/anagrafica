package org.mjhost.anagrafica.enumeration;

public enum RequestHandler {

    FIND_PEOPLE("findPeople"),

    SYSTEM_DASHBOARD("systemDashboard");

    private String value;

    RequestHandler(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
