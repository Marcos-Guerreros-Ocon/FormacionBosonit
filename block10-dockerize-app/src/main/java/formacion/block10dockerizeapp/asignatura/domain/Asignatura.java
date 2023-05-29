package formacion.block10dockerizeapp.asignatura.domain;

import formacion.block10dockerizeapp.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block10dockerizeapp.asignatura.controller.dto.SimpleAsignaturaOutputDto;
import formacion.block10dockerizeapp.student.controller.dto.SimpleStudentOutputDto;
import formacion.block10dockerizeapp.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(mappedBy = "asignaturas")
    Set<Student> estudiantes;

    public AsignaturaOutputDto asignaturaToAsignaturaOutputDto() {
        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto();

        asignaturaOutputDto.setId_asignatura(this.id_asignatura);
        asignaturaOutputDto.setAsignatura(this.getAsignatura());
        asignaturaOutputDto.setComents(this.getComents());
        asignaturaOutputDto.setInitial_date(this.initial_date);
        asignaturaOutputDto.setFinish_date(this.finish_date);
        List<SimpleStudentOutputDto> estudiantes = new ArrayList<>();
        this.estudiantes.forEach(es -> estudiantes.add(es.simpleStudentToStudentOutputDto()));
        asignaturaOutputDto.setEstudiantes(estudiantes);

        return asignaturaOutputDto;
    }

    public SimpleAsignaturaOutputDto asignaturaToSimpleAsignaturaOutputDto() {
        SimpleAsignaturaOutputDto simpleAsignaturaOutputDto = new SimpleAsignaturaOutputDto();
        simpleAsignaturaOutputDto.setId_asignatura(this.id_asignatura);
        simpleAsignaturaOutputDto.setAsignatura(this.getAsignatura());
        simpleAsignaturaOutputDto.setComents(this.getComents());
        simpleAsignaturaOutputDto.setInitial_date(this.initial_date);
        simpleAsignaturaOutputDto.setFinish_date(this.finish_date);
        return simpleAsignaturaOutputDto;
    }


}
