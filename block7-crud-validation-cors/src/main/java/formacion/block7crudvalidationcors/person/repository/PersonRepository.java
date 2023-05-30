package formacion.block7crudvalidationcors.person.repository;

import formacion.block7crudvalidationcors.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}
