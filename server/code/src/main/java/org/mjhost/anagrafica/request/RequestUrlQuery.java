package org.mjhost.anagrafica.request;

public enum RequestUrlQuery {

    DASHBOARD("dashboard"),

    FIND_BY_EMPLOYMENT("findByEmployment"),

    FIND_BY_ID("findById"),

    FIND_BY_HOBBY("findByHobby"),

    FIND_BY_NAME("findByName");

    private String value;

    RequestUrlQuery(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}