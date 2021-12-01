package com.exist.core.repository;

import com.exist.core.data.entity.Person;
import com.exist.core.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);
    List<Person> findByNameLastName(String lastName);
}
