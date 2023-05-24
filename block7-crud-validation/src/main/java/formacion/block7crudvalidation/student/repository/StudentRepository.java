package formacion.block7crudvalidation.student.repository;


import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import formacion.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByPersona(Person persona);
}
