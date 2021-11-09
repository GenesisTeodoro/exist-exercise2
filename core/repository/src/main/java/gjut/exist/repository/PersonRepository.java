package gjut.exist.repository;

import gjut.exist.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByLastName(String lastName);
    Person findById(long id);
}
