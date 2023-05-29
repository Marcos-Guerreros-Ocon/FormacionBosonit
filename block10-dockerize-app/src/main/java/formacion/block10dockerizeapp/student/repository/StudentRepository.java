package formacion.block10dockerizeapp.student.repository;


import formacion.block10dockerizeapp.person.domain.Person;
import formacion.block10dockerizeapp.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByPersona(Person persona);
}
