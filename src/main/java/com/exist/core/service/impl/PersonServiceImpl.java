package com.exist.core.service.impl;


import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Person;
import com.exist.core.data.entity.Role;
import com.exist.core.data.exception.ContactNotFoundException;
import com.exist.core.data.exception.PersonNotFoundException;
import com.exist.core.data.exception.RoleNotFoundException;
import com.exist.core.repository.ContactRepository;
import com.exist.core.repository.PersonRepository;
import com.exist.core.repository.RoleRepository;
import com.exist.core.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private RoleRepository roleRepo;

    //Get All Person
    @Override
    public List<PersonDTO> getAllPerson(String lastName) {

        List<Person> personList;

        if (lastName == null)
           personList = personRepo.findAll();
        else
           personList = personRepo.findByNameLastName(lastName);

        return personList.stream()
                .map(person -> mapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());

    }

    //Get Person By Id
    @Override
    public PersonDTO getPersonById(long id) {
        Optional<Person> personData = personRepo.findById(id);
        Person personResponse;

        if (personData.isPresent()) {
            personResponse = personData.get();
        } else {
            throw new PersonNotFoundException(id);
        }

        return mapper.map(personResponse, PersonDTO.class);

    }


    //Add Person
    @Override
    public PersonDTO addPerson(PersonDTO personRequest) {

        //convert DTO to Entity
        Person person = mapper.map(personRequest, Person.class);

        personRepo.save(new Person(
                person.getName(),
                person.getAddress(),
                person.getBirthday(),
                person.getGwa(),
                person.getDateHired(),
                person.isCurrentlyEmployed(),
                person.getEmployeeReference(),
                person.getEmployeeRating(),
                person.getContacts(),
                person.getRoles()
        ));

        //Convert Entity to DTO
        PersonDTO personResponse = mapper.map(person, PersonDTO.class);

        return personResponse;

    }


    //Update Person
    @Override
    public PersonDTO updatePerson(long id, PersonDTO personRequest) {

        PersonDTO personResponse;

        //Convert DTO to Entity
        Person person = mapper.map(personRequest, Person.class);

        Optional<Person> personData = personRepo.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setName(person.getName());
            _person.setAddress(person.getAddress());
            _person.setBirthday(person.getBirthday());
            _person.setGwa(person.getGwa());
            _person.setDateHired(person.getDateHired());
            _person.setCurrentlyEmployed(person.isCurrentlyEmployed());
            _person.setEmployeeReference(person.getEmployeeReference());
            _person.setEmployeeRating(person.getEmployeeRating());
            _person.setContacts(person.getContacts());
            _person.setRoles(person.getRoles());

            personRepo.save(_person);

            personResponse = mapper.map(_person, PersonDTO.class);
        } else {
            throw new PersonNotFoundException(id);
        }

        return personResponse;
    }

    //Delete Person
    @Override
    public void deletePerson(long id) {
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepo.deleteById(id);

    }

    //Delete All Person
    @Override
    public void deleteAllPerson() {
        personRepo.deleteAll();
    }

    @Override
    public ContactDTO createPersonContact(long personId, ContactDTO contactRequest) {
        ContactDTO contactDto;
        Contact contact = mapper.map(contactRequest, Contact.class);

        Optional<Person> person = personRepo.findById(personId);

        if(person.isPresent()){
            contact.setPersonId(personId);

            contactRepo.save(new Contact(
                    contact.getContactType(),
                    contact.getContactInfo(),
                    contact.getContactOrder()
            ));

            Person _person = person.get();
            _person.getContacts().add(contact);
            personRepo.save(_person);

            contactDto = mapper.map(contact, ContactDTO.class);
        }else{
            throw new PersonNotFoundException(personId);
        }

        return contactDto;
    }

    @Override
    public void deletePersonContact(long personId, long contactId) {

        Contact contact = contactRepo.findContactByIdAndPersonId(personId, contactId)
                .orElseThrow(() -> new ContactNotFoundException(personId, contactId));

        contactRepo.delete(contact);
    }

    @Override
    public void addPersonRole(long personId, long roleId) {
        Optional<Person> person = personRepo.findById(personId);

        if(person.isPresent()){
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));

            Person _person = person.get();
            _person.getRoles().add(role);
            personRepo.save(_person);

        }else{
            throw new PersonNotFoundException(personId);
        }


    }

    @Override
    public void deletePersonRole(long personId, long roleId) {
        Optional<Person> person = personRepo.findById(personId);
        Role role = roleRepo.findByRoleId(roleId);

        if(person.isPresent()){
            Person _person = person.get();
            _person.getRoles().remove(role);
            personRepo.save(_person);
        }else{
            throw new PersonNotFoundException(personId);
        }
    }


}
