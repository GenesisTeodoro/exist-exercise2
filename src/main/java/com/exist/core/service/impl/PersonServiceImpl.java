package com.exist.core.service.impl;


import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Person;
import com.exist.core.data.entity.Role;
import com.exist.core.data.exception.ContactNotFoundException;
import com.exist.core.data.exception.PersonNotFoundException;
import com.exist.core.data.exception.RoleNotFoundException;
import com.exist.core.data.mapper.CoreMapper;
import com.exist.core.repository.ContactRepository;
import com.exist.core.repository.PersonRepository;
import com.exist.core.repository.RoleRepository;
import com.exist.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    public CoreMapper mapper;

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
                .map(person -> mapper.toDto(person))
                .collect(Collectors.toList());

    }

    //Get Person By Id
    @Override
    public PersonDTO getPersonById(long id) {
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return mapper.toDto(person);

    }


    //Add Person
    @Override
    public PersonDTO addPerson(PersonDTO personRequest) {

        Person person = mapper.toEntity(personRequest);

        return mapper.toDto(personRepo.save(person));

    }


    //Update Person
    @Override
    public PersonDTO updatePerson(long id, PersonDTO personRequest) {
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        mapper.updateEntity(personRequest, person);

        personRepo.save(person);

        return mapper.toDto(person);

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
        Contact contact = mapper.toEntity(contactRequest);

        Optional<Person> person = personRepo.findById(personId);

        if(person.isPresent()){
            contact.setPersonId(personId);

            contactRepo.save(contact);

            Person _person = person.get();
            _person.getContacts().add(contact);
            personRepo.save(_person);

            contactDto = mapper.toDto(contact);
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
