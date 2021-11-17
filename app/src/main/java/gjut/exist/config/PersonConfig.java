package gjut.exist.config;

import gjut.exist.model.*;
import gjut.exist.repository.PersonRepository;
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
