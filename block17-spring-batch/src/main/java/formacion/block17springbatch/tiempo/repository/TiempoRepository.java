package formacion.block17springbatch.tiempo.repository;


import formacion.block17springbatch.tiempo.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepository extends JpaRepository<Tiempo,Integer> {
}
