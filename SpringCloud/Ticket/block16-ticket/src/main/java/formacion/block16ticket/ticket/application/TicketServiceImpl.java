package formacion.block16ticket.ticket.application;


import formacion.block16ticket.exception.EntityNotFoundException;
import formacion.block16ticket.ticket.controller.dto.TicketOutputDto;
import formacion.block16ticket.ticket.domain.Cliente;
import formacion.block16ticket.ticket.domain.Ticket;
import formacion.block16ticket.ticket.domain.Viaje;
import formacion.block16ticket.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FeignViaje feignViaje;

    @Autowired
    FeignCliente feignCliente;

    @Override
    public TicketOutputDto crearTicket(int idPasajero, int idViaje) {
        Cliente cliente = feignCliente.getClienteById(idPasajero);
        Viaje viaje = feignViaje.getViajeById(idViaje);


        Ticket ticket = new Ticket();
        ticket.setPassengerId(cliente.getId());
        ticket.setPassengerName(cliente.getNombre());
        ticket.setPassengerLastname(cliente.getApellido());
        ticket.setPassengerEmail(cliente.getEmail());
        ticket.setTripOrigin(viaje.getOrigin());
        ticket.setTripDestination(viaje.getDestination());
        ticket.setDepartureDate(viaje.getDepartureDate());
        ticket.setArrivalDate(viaje.getArrivalDate());

        return ticketRepository.save(ticket).ticketToTicketOutputDto();
    }

    @Override
    public TicketOutputDto obtenerTickerPorId(int id) throws EntityNotFoundException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isEmpty()) throw new EntityNotFoundException("No existe un ticket con id: " + id);

        return optionalTicket.get().ticketToTicketOutputDto();
    }
}

