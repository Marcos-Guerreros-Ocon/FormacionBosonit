package formacion.block16springcloud.cliente.repository;

import formacion.block16springcloud.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
