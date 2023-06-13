package formacion.block16ticket.ticket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Viaje {

    @Id
    @GeneratedValue
    Integer idViaje;

    String origin;

    String destination;

    String departureDate;

    String arrivalDate;

    @ManyToMany
    List<Cliente> passengers=new ArrayList<>();

    String status="Disponible";
}