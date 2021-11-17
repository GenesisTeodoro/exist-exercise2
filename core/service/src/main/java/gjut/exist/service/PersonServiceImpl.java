package gjut.exist.service;

import gjut.exist.model.Person;
import gjut.exist.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    //Get All Person
    @Override
    public ResponseEntity<List<Person>> getAllPerson(@RequestParam(required = false) String lastName) {
        try{
            List<Person> personList = new ArrayList<Person>();

            if (lastName == null)
                repository.findAll().forEach(personList::add);
            else
                repository.findByNameLastName(lastName).forEach(personList::add);

            if (personList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(personList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Person By Id
    @Override
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            return new ResponseEntity<>(personData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Add Person
    @Override
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        try {
            Person _person = repository
                    .save(new Person(
                            person.getName(),
                            person.getAddress(),
                            person.getBirthday(),
                            person.getGwa(),
                            person.getDateHired(),
                            person.getCurrentlyEmployed(),
                            person.getEmployeeReference(),
                            person.getContacts(),
                            person.getRoles()
                    ));
            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Update Person
    @Override
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setName(person.getName());
            _person.setAddress(person.getAddress());
            _person.setBirthday(person.getBirthday());
            _person.setGwa(person.getGwa());
            _person.setDateHired(person.getDateHired());
            _person.setCurrentlyEmployed(person.getCurrentlyEmployed());
            _person.setEmployeeReference(person.getEmployeeReference());
            _person.setContacts(person.getContacts());
            _person.setRoles(person.getRoles());



            return new ResponseEntity<>(repository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete Person
    @Override
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete All Person
    @Override
    public ResponseEntity<HttpStatus> deleteAllPerson() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
