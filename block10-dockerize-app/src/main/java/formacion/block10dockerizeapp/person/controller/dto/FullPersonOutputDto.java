package formacion.block10dockerizeapp.person.controller.dto;

import formacion.block10dockerizeapp.student.controller.dto.SimpleStudentOutputDto;
import formacion.block10dockerizeapp.teacher.controller.dto.SimpleTeacherOutputDto;
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
