package formacion.block17springbatch.tiemporiesgo.repository;

import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRiesgoRepository extends JpaRepository<TiempoRiesgo,Integer> {
}
