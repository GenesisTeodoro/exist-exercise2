package com.exist.core.data.dto;


import com.exist.core.data.entity.Address;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Name;
import com.exist.core.data.entity.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PersonDTO {
    private long id;
    private Name name;
    private Address address;
    private Date birthday;
    private double gwa;
    private Date dateHired;
    private boolean currentlyEmployed;
    private String employeeReference;
    private Set<Contact> contacts;
    private Set<Role> roles;

}
