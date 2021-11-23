package com.exist.core.config;

import com.exist.core.data.entity.*;
import com.exist.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class PersonConfig {


    @Bean
    CommandLineRunner commandLineRunner(
            PersonRepository repository){

        Set<Contact> contacts = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        Name name = new Name(
                "FirstName", "MiddleName",
                "LastName", "", ""
        );

        Address address = new Address(
            "UnitNo", "Str", "Brgy", "Subdivision",
                "Municipality", "Province", 1810
        );

        return args -> {
            Person personExample = new Person(
                    name,
                    address,
                    new Date(),
                    2.5,
                    new Date(),
                    true,
                    "EmployeeReference",
                    contacts,
                    roles
            );

            repository.saveAll(
                    List.of(personExample)
            );

        };
    }
}
