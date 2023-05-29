package formacion.block10dockerizeapp.teacher.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutputDto extends SimpleTeacherOutputDto {
    Integer id_persona;
}
