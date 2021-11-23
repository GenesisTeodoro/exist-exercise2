package com.exist.core.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="Person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Name name;
    @Embedded
    private Address address;
    @Temporal(TemporalType.DATE)
    @Column(name="birthday")
    private Date birthday;
    @Column(name="gwa")
    private double gwa;
    @Column(name="date_hired")
    private Date dateHired;
    @Column(name="currently_employed")
    private boolean currentlyEmployed;

    @Column(name="employee_reference")
    private String employeeReference;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private Set<Contact> contacts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="person_role", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Person(Name name,
                  Address address, Date birthday,
                  double gwa, Date dateHired,
                  boolean currentlyEmployed,
                  String employeeReference,
                  Set<Contact> contacts,
                  Set<Role> roles){
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.gwa = gwa;
        this.dateHired = dateHired;
        this.currentlyEmployed = currentlyEmployed;
        this.employeeReference = employeeReference;
        this.contacts = contacts;
        this.roles = roles;

    }

}
