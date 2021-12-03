package com.exist.core.service;

import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.dto.RoleDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPerson(String lastName);
    PersonDTO addPerson(PersonDTO personDTO);
    PersonDTO updatePerson(long id, PersonDTO personDTO);
    PersonDTO getPersonById(long id);
    void deletePerson(long id);
    void deleteAllPerson();
    ContactDTO createPersonContact(long personId, ContactDTO contactDto);
    void deletePersonContact(long personId, long contactId);
    void addPersonRole(long personId, long roleId);
    void deletePersonRole(long personId, long roleId);
}
