package com.exist.core.data.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Could not find Person: " + id);
    }
}
