package formacion.block16springcloud.viaje.controller.dto;

import formacion.block16springcloud.cliente.controller.dto.ClienteOutputDto;
import formacion.block16springcloud.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViajeOutputDto {
    private Integer idViaje;
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String status;
    private List<ClienteOutputDto> passengers = new ArrayList<>();
}