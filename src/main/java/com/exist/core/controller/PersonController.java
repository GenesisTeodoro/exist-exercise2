package com.exist.core.controller;

import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.dto.RoleDTO;
import com.exist.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPerson(@RequestParam(required = false) String lastName){
        return new ResponseEntity<>(personService.getAllPerson(lastName), OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") long id) {
        return new ResponseEntity<>(personService.getPersonById(id), OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(personService.addPerson(personDTO), CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(personService.updatePerson(id, personDTO), OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPerson(){
        personService.deleteAllPerson();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/contacts")
    public ResponseEntity<ContactDTO> createPersonContact(
            @PathVariable("id") long personId, @RequestBody ContactDTO contactDto){
        personService.createPersonContact(personId, contactDto);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping(path = "/{id}/contacts/{contactId}")
    public ResponseEntity<HttpStatus> deletePersonContact(
            @PathVariable("id") long personId,
            @PathVariable("contactId") long contactId){
        personService.deletePersonContact(personId, contactId);
        return new ResponseEntity<>(OK);
    }

    @PostMapping(path = "/{id}/role/{roleId}")
    public ResponseEntity<RoleDTO> addPersonRole(
            @PathVariable("id") long personId, @PathVariable("roleId") long roleId){
        personService.addPersonRole(personId, roleId);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping(path = "/{id}/role/{roleId}")
    public ResponseEntity<HttpStatus> deletePersonRole(
            @PathVariable("id") long personId,
            @PathVariable("roleId") long roleId){
        personService.deletePersonRole(personId, roleId);
        return new ResponseEntity<>(OK);
    }







}
