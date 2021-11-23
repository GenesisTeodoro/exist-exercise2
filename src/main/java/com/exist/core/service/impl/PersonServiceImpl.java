package com.exist.core.service.impl;


import com.exist.core.data.entity.Person;
import com.exist.core.data.exception.PersonNotFoundException;
import com.exist.core.repository.PersonRepository;
import com.exist.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Person> getAllPerson(@RequestParam(required = false) String lastName) {

        List<Person> personList = new ArrayList<Person>();

        if (lastName == null)
           repository.findAll().forEach(personList::add);
        else
           repository.findByNameLastName(lastName).forEach(personList::add);

        return personList;

    }

    //Get Person By Id
    @Override
    public Person getPersonById(@PathVariable("id") long id) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            return personData.get();
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    //Add Person
    @Override
    public Person addPerson(@RequestBody Person person) {
        Person _person = repository.save(new Person(
                person.getName(),
                person.getAddress(),
                person.getBirthday(),
                person.getGwa(),
                person.getDateHired(),
                person.isCurrentlyEmployed(),
                person.getEmployeeReference(),
                person.getContacts(),
                person.getRoles()
        ));

        return _person;

    }


    //Update Person
    @Override
    public Person updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setName(person.getName());
            _person.setAddress(person.getAddress());
            _person.setBirthday(person.getBirthday());
            _person.setGwa(person.getGwa());
            _person.setDateHired(person.getDateHired());
            _person.setCurrentlyEmployed(person.isCurrentlyEmployed());
            _person.setEmployeeReference(person.getEmployeeReference());
            _person.setContacts(person.getContacts());
            _person.setRoles(person.getRoles());

            return repository.save(_person);
        } else {
            throw new PersonNotFoundException(id);
        }


    }

    //Delete Person
    @Override
    public void deletePerson(@PathVariable("id") long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        repository.deleteById(id);

    }

    //Delete All Person
    @Override
    public void deleteAllPerson() {
        repository.deleteAll();
    }


}
