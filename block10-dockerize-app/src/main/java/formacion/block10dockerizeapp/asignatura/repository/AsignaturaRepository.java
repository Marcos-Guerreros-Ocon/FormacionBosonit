package formacion.block10dockerizeapp.asignatura.repository;

import formacion.block10dockerizeapp.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

}
