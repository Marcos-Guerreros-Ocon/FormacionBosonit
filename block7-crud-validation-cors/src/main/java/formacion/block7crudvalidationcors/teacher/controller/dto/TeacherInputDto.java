package formacion.block7crudvalidationcors.teacher.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInputDto {

    Integer id_persona;
    String coments;
    String branch;
}
