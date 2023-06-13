package formacion.block16springcloud.viaje.repository;

import formacion.block16springcloud.viaje.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
