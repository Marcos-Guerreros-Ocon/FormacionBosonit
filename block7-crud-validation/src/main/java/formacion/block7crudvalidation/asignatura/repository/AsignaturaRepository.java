package formacion.block7crudvalidation.asignatura.repository;

import formacion.block7crudvalidation.asignatura.domain.Asignatura;
import formacion.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

}
