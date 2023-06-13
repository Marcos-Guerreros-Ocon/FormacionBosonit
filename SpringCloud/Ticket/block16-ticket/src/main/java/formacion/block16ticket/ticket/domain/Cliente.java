package formacion.block16ticket.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class Cliente {

    @Id
    @GeneratedValue
    Integer id;

    String nombre;

    String apellido;

    Integer edad;

    String email;

    String telefono;
}