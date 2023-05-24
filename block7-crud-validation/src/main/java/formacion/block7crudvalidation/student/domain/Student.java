package formacion.block7crudvalidation.student.domain;

import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Estudiante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_student;

    @OneToOne
    @JoinColumn(name = "id_persona")
    Person persona;

    @Column(name = "horas_por_semana")
    @NotNull
    Integer num_hours_week;

    @Column(name = "comentarios")
    String coments;


    @NotNull
    String branch;
/*
    @ManyToOne
    @JoinColumn(name = "id_profesor")
    Profesor profesor;
*/


/*    @OneToMany
    List<Asignatura> asignaturas;*/

    public Student(StudentInputDto studentInputDto) {
        this.id_student = studentInputDto.getId_student();
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.coments = studentInputDto.getComents();
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentToStudentOutputDto() {
        StudentOutputDto studentOutputDto = new StudentOutputDto();

        studentOutputDto.setId_student(this.id_student);
        studentOutputDto.setPersona(this.persona);
        studentOutputDto.setNum_hours_week(this.num_hours_week);
        studentOutputDto.setComents(this.coments);
        studentOutputDto.setBranch(this.branch);

        return studentOutputDto;
    }

    public SimpleStudentOutputDto simpleStudentToStudentOutputDto() {
        SimpleStudentOutputDto studentOutputDto = new SimpleStudentOutputDto();

        studentOutputDto.setId_student(this.id_student);
        studentOutputDto.setNum_hours_week(this.num_hours_week);
        studentOutputDto.setComents(this.coments);
        studentOutputDto.setBranch(this.branch);

        return studentOutputDto;
    }
}