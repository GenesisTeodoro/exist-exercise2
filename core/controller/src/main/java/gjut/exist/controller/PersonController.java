package gjut.exist.controller;

import gjut.exist.controller.exceptions.PersonNotFoundException;
import gjut.exist.model.Person;
import gjut.exist.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {


    private final PersonRepository repository;

    PersonController(PersonRepository repository){
        this.repository = repository;
    }

    //Get All Person
    @GetMapping("/persons")
    List<Person> all() {
        return repository.findAll();
    }

    //Get Person
    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    //Add Person
    @PostMapping("/persons")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @PutMapping("/employees/{id}")
    Person editPerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setAddress(newPerson.getAddress());
                    person.setBirthday(newPerson.getBirthday());
                    person.setGwa(newPerson.getGwa());
                    person.setDateHired(newPerson.getDateHired());
                    person.setCurrentlyEmployed(newPerson.getCurrentlyEmployed());
                    person.setContacts(newPerson.getContacts());
                    person.setRoles(newPerson.getRoles());
                    person.setEmployeeReference(newPerson.getEmployeeReference());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    //Delete Person
    @DeleteMapping("/persons/{id}")
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }



}
