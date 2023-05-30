package formacion.block7crudvalidationcors.asignatura.controller.dto;


import formacion.block7crudvalidationcors.student.controller.dto.SimpleStudentOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDto extends SimpleAsignaturaOutputDto {

    private List<SimpleStudentOutputDto> estudiantes;
}
