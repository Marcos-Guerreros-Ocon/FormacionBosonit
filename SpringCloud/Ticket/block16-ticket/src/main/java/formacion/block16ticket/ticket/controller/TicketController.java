package formacion.block16ticket.ticket.controller;

import formacion.block16ticket.exception.EntityNotFoundException;
import formacion.block16ticket.ticket.application.TicketService;
import formacion.block16ticket.ticket.controller.dto.TicketOutputDto;
import formacion.block16ticket.ticket.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;


    @PostMapping("/generateTicket/{idPassenger}/{idTrip}")
    public ResponseEntity<TicketOutputDto> generateTicket(@PathVariable("idPassenger") Integer idPassenger,
                                                          @PathVariable("idTrip") Integer idTrip) {

        URI location = URI.create("/ticket");
        return ResponseEntity.created(location).body(ticketService.crearTicket(idPassenger, idTrip));

    }

    @GetMapping("{idTicket}")
    public ResponseEntity<TicketOutputDto> getTicket(@PathVariable("idTicket") Integer idTicket) {
        try {
            return ResponseEntity.ok(ticketService.obtenerTickerPorId(idTicket));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

}