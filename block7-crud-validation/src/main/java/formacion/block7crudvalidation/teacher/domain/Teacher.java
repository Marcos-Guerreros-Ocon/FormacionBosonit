package formacion.block7crudvalidation.teacher.domain;

import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    Integer idProfesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    Person person;

    String coments;

    @NotNull
    String branch;

    public Teacher(TeacherInputDto teacherInputDto) {

        this.coments = teacherInputDto.getComents();
        this.branch = teacherInputDto.getBranch();
    }

    public TeacherOutputDto teacherToTeacherOutputDto() {
        TeacherOutputDto teacherOutputDto = new TeacherOutputDto();

        teacherOutputDto.setIdProfesor(this.idProfesor);
        teacherOutputDto.setId_persona(this.person.getId());
        teacherOutputDto.setComents(this.coments);
        teacherOutputDto.setBranch(this.branch);

        return teacherOutputDto;
    }

    public SimpleTeacherOutputDto teacherToSimpleTeacherOutputDto() {
        SimpleTeacherOutputDto simpleTeacherOutputDto = new SimpleTeacherOutputDto();
        simpleTeacherOutputDto.setIdProfesor(this.idProfesor);
        simpleTeacherOutputDto.setComents(this.coments);
        simpleTeacherOutputDto.setBranch(this.branch);

        return simpleTeacherOutputDto;
    }

}
