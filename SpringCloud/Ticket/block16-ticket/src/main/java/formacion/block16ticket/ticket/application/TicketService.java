package formacion.block16ticket.ticket.application;


import formacion.block16ticket.exception.EntityNotFoundException;
import formacion.block16ticket.ticket.controller.dto.TicketOutputDto;

public interface TicketService {
    TicketOutputDto crearTicket(int idPasajero, int idViaje);

    TicketOutputDto obtenerTickerPorId(int id) throws EntityNotFoundException;

}
