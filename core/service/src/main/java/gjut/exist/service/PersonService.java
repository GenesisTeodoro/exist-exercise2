package gjut.exist.service;

import gjut.exist.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    ResponseEntity<List<Person>> getAllPerson(String lastName);
    ResponseEntity<Person> addPerson(Person person);
    ResponseEntity<Person> updatePerson(long id, Person person);
    ResponseEntity<Person> getPersonById(long id);
    ResponseEntity<HttpStatus> deletePerson(long id);
    ResponseEntity<HttpStatus> deleteAllPerson();
}
