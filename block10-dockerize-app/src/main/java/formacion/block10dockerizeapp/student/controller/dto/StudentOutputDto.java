package formacion.block10dockerizeapp.student.controller.dto;


import formacion.block10dockerizeapp.asignatura.controller.dto.SimpleAsignaturaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto extends SimpleStudentOutputDto{
    Integer id_persona;
    List<SimpleAsignaturaOutputDto> asignaturas;

}
