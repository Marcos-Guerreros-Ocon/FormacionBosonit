package formacion.block7crudvalidation.asignatura.repository;

import formacion.block7crudvalidation.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
