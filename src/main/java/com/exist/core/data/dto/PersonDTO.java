package com.exist.core.data.dto;


import com.exist.core.data.entity.Address;
import com.exist.core.data.entity.Contact;
import com.exist.core.data.entity.Name;
import com.exist.core.data.entity.Role;
import lombok.Data;

import java.math.BigDecimal;
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
    private BigDecimal employeeRating;
    private Set<ContactDTO> contacts;
    private Set<RoleDTO> roles;

}
