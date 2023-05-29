package formacion.block10dockerizeapp.student.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleStudentOutputDto {


    Integer id_student;
    Integer num_hours_week;
    String coments;
    String branch;


}
