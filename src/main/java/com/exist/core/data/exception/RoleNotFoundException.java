package com.exist.core.data.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(Long id) {
        super("Could not find Role: " + id);
    }

    public RoleNotFoundException(String roleType){
        super("Could not find Role: " + roleType);
    }

    public RoleNotFoundException(Long personId, Long roleId){
        super("Could not find Role: " + roleId + "for Person: " + personId);
    }
}
