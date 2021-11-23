package com.exist.core.controller;

import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.entity.Person;
import com.exist.core.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonService service;

    @GetMapping
    public List<PersonDTO> getAllPerson(@RequestParam(required = false) String lastName){
        return service.getAllPerson(lastName).stream()
                .map(person -> mapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") long id) {
        Person person = service.getPersonById(id);

        //Convert Entity to DTO
        PersonDTO personResponse = mapper.map(person, PersonDTO.class);

        return ResponseEntity.ok().body(personResponse);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO){

        //convert DTO to Entity
        Person personRequest = mapper.map(personDTO, Person.class);

        Person person = service.addPerson(personRequest);

        //Convert Entity to DTO
        PersonDTO personResponse = mapper.map(person, PersonDTO.class);

        return new ResponseEntity<PersonDTO>(personResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO){
        //Convert DTO to Entity
        Person personRequest = mapper.map(personDTO, Person.class);

        Person person = service.updatePerson(id, personRequest);

        //Entity to DTO
        PersonDTO personResponse = mapper.map(person, PersonDTO.class);

        return ResponseEntity.ok().body(personResponse);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id){
        service.deletePerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPerson(){
        service.deleteAllPerson();

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
