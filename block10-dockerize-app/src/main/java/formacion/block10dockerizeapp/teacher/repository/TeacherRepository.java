package formacion.block10dockerizeapp.teacher.repository;

import formacion.block10dockerizeapp.person.domain.Person;
import formacion.block10dockerizeapp.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByPerson(Person person);

}
