package org.mjhost.anagrafica.exception;

import graphql.GraphQLError;

import java.util.LinkedList;
import java.util.List;

public class PersonException extends Exception {

    private List<GraphQLError> errors = new LinkedList<>();

    public PersonException() {
        super();
    }

    public PersonException(String message) {
        super(message);
    }

    public PersonException(Throwable cause) {
        super(cause);
    }

    public PersonException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    public void setErrors(List<GraphQLError> errors) {
        this.errors = errors;
    }
}
