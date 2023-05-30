package formacion.block7crudvalidationcors.person.controller.dto;

import formacion.block7crudvalidationcors.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidationcors.teacher.controller.dto.SimpleTeacherOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullPersonOutputDto extends PersonOutputDto {
    SimpleTeacherOutputDto teacher;
    SimpleStudentOutputDto student;
}
