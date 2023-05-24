package formacion.block7crudvalidation.asignatura.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDto {
    private Integer id_asignatura;
    private String asignatura;
    private String coments;
    private Date initial_date;
    private Date finish_date;
    private List<Integer> estudiantes = new ArrayList<>();
}
