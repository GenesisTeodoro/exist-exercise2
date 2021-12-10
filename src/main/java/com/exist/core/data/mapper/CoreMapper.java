package com.exist.core.data.mapper;


import com.exist.core.data.dto.ContactDTO;
import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.dto.RoleDTO;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Person;
import com.exist.core.data.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoreMapper {

    PersonDTO toDto(Person person);

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(PersonDTO dto, @MappingTarget Person person);

    RoleDTO toDto(Role role);

    @Mapping(target = "roleId", ignore = true)
    Role toEntity(RoleDTO dto);

    @Mapping(target = "roleId", ignore = true)
    void updateEntity(RoleDTO dto, @MappingTarget Role role);

    ContactDTO toDto(Contact contact);

    @Mapping(target = "id", ignore = true)
    Contact toEntity(ContactDTO dto);




}
