package formacion.block16springcloud.viaje.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViajeInputDto {
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
}