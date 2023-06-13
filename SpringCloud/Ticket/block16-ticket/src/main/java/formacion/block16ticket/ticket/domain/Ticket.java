package formacion.block16ticket.ticket.domain;


import formacion.block16ticket.ticket.controller.dto.TicketInputDto;
import formacion.block16ticket.ticket.controller.dto.TicketOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    Integer id;
    Integer passengerId;
    String passengerName;
    String passengerLastname;
    String passengerEmail;
    String tripOrigin;
    String tripDestination;
    String departureDate;
    String arrivalDate;

    public TicketOutputDto ticketToTicketOutputDto() {
        TicketOutputDto ticketOutputDto = new TicketOutputDto();

        ticketOutputDto.setId(getId());
        ticketOutputDto.setPassengerId(getPassengerId());
        ticketOutputDto.setPassengerName(getPassengerName());
        ticketOutputDto.setPassengerLastname(getPassengerLastname());
        ticketOutputDto.setPassengerEmail(getPassengerEmail());
        ticketOutputDto.setTripOrigin(getTripOrigin());
        ticketOutputDto.setTripDestination(getTripDestination());
        ticketOutputDto.setDepartureDate(getDepartureDate());
        ticketOutputDto.setArrivalDate(getArrivalDate());

        return ticketOutputDto;
    }

    public Ticket(TicketInputDto ticketInputDto) {
        setPassengerId(ticketInputDto.getPassengerId());
        setPassengerName(ticketInputDto.getPassengerName());
        setPassengerLastname(ticketInputDto.getPassengerLastname());
        setPassengerEmail(ticketInputDto.getPassengerEmail());
        setTripOrigin(ticketInputDto.getTripOrigin());
        setTripDestination(ticketInputDto.getTripDestination());
        setDepartureDate(ticketInputDto.getDepartureDate());
        setArrivalDate(ticketInputDto.getArrivalDate());
    }
}
