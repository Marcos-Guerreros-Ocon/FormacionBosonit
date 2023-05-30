package formacion.block7crudvalidationcors.student.repository;


import formacion.block7crudvalidationcors.person.domain.Person;
import formacion.block7crudvalidationcors.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByPersona(Person persona);
}
