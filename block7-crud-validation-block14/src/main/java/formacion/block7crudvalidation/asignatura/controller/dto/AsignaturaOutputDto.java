package formacion.block7crudvalidation.asignatura.controller.dto;


import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
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
public class AsignaturaOutputDto extends SimpleAsignaturaOutputDto {

    private List<SimpleStudentOutputDto> estudiantes;
}
