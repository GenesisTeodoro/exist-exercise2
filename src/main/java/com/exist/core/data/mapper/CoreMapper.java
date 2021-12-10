package com.exist.core.data.mapper;


import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.dto.RoleDTO;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Person;
import com.exist.core.data.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoreMapper {

    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO dto);

    void updateEntity(PersonDTO dto, @MappingTarget Person person);

    RoleDTO toDto(Role role);

    Role toEntity(RoleDTO dto);

    void updateEntity(RoleDTO dto, @MappingTarget Role role);

    ContactDTO toDto(Contact contact);

    Contact toEntity(ContactDTO dto);




}
