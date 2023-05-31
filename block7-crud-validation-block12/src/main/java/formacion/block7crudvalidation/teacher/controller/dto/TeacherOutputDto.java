package formacion.block7crudvalidation.teacher.controller.dto;

import formacion.block7crudvalidation.person.domain.Person;
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
