package formacion.block7crudvalidation.person.controller.dto;

import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullPersonOutputDto extends PersonOutputDto{
    Teacher teacher;
    Student student;
}
