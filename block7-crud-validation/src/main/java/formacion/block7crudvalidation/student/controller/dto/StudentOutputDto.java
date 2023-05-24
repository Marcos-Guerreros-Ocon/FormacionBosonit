package formacion.block7crudvalidation.student.controller.dto;


import formacion.block7crudvalidation.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto extends SimpleStudentOutputDto{
    Person persona;

}
