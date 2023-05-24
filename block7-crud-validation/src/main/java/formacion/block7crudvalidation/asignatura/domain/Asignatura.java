package formacion.block7crudvalidation.asignatura.domain;

import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    Integer id_asignatura;

    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
    @OneToMany(mappedBy = "asignaturas")
    List<Student> estudiantes;

    public AsignaturaOutputDto asignaturaToAsignaturaOutputDto() {
        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto();

        asignaturaOutputDto.setId_asignatura(this.id_asignatura);
        asignaturaOutputDto.setAsignatura(this.getAsignatura());
        asignaturaOutputDto.setComents(this.getComents());
        asignaturaOutputDto.setInitial_date(this.initial_date);
        asignaturaOutputDto.setFinish_date(this.finish_date);


        List<Integer> estudiantes = new ArrayList<>();
        this.estudiantes.forEach(es -> estudiantes.add(es.getId_student()));
        asignaturaOutputDto.setEstudiantes(estudiantes);

        return asignaturaOutputDto;
    }


}
