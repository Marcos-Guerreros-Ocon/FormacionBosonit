package formacion.block7crudvalidation.teacher.repository;

import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import formacion.block7crudvalidation.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByPerson(Person person);
}
