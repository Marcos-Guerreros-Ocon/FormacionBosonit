package formacion.block16ticket.ticket.repository;

import formacion.block16ticket.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
