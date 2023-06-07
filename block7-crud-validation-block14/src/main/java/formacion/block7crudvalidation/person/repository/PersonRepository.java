package formacion.block7crudvalidation.person.repository;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    Person findByUsuario(String usuario);
}
