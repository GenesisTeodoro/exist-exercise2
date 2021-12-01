package com.exist.core.data.exception;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(Long personId, Long roleId) {
        super("Person " + personId + " with Role " + roleId + "already exists");
    }

}
