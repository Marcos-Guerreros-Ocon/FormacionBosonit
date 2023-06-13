package formacion.block16ticket.ticket.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketInputDto {
    Integer passengerId;
    String passengerName;
    String passengerLastname;
    String passengerEmail;
    String tripOrigin;
    String tripDestination;
    String departureDate;
    String arrivalDate;
}
