package formacion.block10dockerizeapp.asignatura.controller.dto;


import formacion.block10dockerizeapp.student.controller.dto.SimpleStudentOutputDto;
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
