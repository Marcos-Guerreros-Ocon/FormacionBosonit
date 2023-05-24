package formacion.block7crudvalidation.student.controller.dto;


import formacion.block7crudvalidation.asignatura.domain.Asignatura;
import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.student.domain.Student;
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
    List<Asignatura> asignaturas;

}
