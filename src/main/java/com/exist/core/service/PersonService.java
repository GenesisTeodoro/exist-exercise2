package com.exist.core.service;

import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson(String lastName);
    Person addPerson(Person person);
    Person updatePerson(long id, Person person);
    Person getPersonById(long id);
    void deletePerson(long id);
    void deleteAllPerson();
}
