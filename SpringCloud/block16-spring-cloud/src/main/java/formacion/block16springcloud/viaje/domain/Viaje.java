package formacion.block16springcloud.viaje.domain;

import formacion.block16springcloud.cliente.domain.Cliente;
import formacion.block16springcloud.viaje.controller.dto.ViajeInputDto;
import formacion.block16springcloud.viaje.controller.dto.ViajeOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue
    Integer idViaje;

    String origin;

    String destination;

    String departureDate;

    String arrivalDate;

    @ManyToMany
    List<Cliente> passengers = new ArrayList<>();

    String status = "Disponible";


    public ViajeOutputDto viajeToViajeOutputDto() {
        ViajeOutputDto viajeOutputDto = new ViajeOutputDto();

        viajeOutputDto.setIdViaje(getIdViaje());
        viajeOutputDto.setOrigin(getOrigin());
        viajeOutputDto.setDestination(getDestination());
        viajeOutputDto.setDepartureDate(getDepartureDate());
        viajeOutputDto.setArrivalDate(getArrivalDate());
        viajeOutputDto.setPassengers(getPassengers().stream().map(Cliente::clienteToClienteOutput).toList());
        viajeOutputDto.setStatus(getStatus());

        return viajeOutputDto;
    }

    public Viaje(ViajeInputDto viajeInputDto) {
        setOrigin(viajeInputDto.getOrigin());
        setDestination(viajeInputDto.getDestination());
        setDepartureDate(viajeInputDto.getDepartureDate());
        setArrivalDate(viajeInputDto.getArrivalDate());
    }
}