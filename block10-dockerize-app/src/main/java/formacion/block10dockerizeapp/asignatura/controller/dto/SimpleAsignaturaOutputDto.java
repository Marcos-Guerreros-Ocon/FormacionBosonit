package formacion.block10dockerizeapp.asignatura.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleAsignaturaOutputDto {
    Integer id_asignatura;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}
