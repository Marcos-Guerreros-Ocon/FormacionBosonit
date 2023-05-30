package formacion.block7crudvalidationcors.student.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {

    Integer id_persona;
    Integer num_hours_week;
    String coments;
    String branch;
    List<Integer> id_asignaturas;
}
