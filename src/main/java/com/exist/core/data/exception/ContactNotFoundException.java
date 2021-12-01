package com.exist.core.data.exception;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(Long personId, Long contactId){
        super("Could not find contact: " + contactId + "for person" + personId);
    }
}
