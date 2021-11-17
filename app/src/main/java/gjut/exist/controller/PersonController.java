package gjut.exist.controller;

import gjut.exist.exception.PersonNotFoundException;
import gjut.exist.model.Person;
import gjut.exist.repository.PersonRepository;
import gjut.exist.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson(@RequestParam(required = false) String lastName){
        return service.getAllPerson(lastName);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        return service.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        return service.addPerson(person);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person){
        return service.updatePerson(id, person);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id){
        return service.deletePerson(id);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPerson(){
        return service.deleteAllPerson();
    }





}
