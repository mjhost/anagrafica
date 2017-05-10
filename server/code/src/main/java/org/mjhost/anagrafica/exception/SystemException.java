package org.mjhost.anagrafica.exception;

import graphql.GraphQLError;

import java.util.LinkedList;
import java.util.List;

public class SystemException extends Exception {

    private List<GraphQLError> errors = new LinkedList<>();

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    public void setErrors(List<GraphQLError> errors) {
        this.errors = errors;
    }
}
