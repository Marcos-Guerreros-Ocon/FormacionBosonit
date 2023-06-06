package formacion.block7crudvalidation;

import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import org.junit.jupiter.api.Assertions;

public class AgregarProfesor {

    public static void agregarProfesor(int idPersona,TeacherInputDto teacherInputDto) {
        teacherInputDto.setId_persona(idPersona);
        teacherInputDto.setBranch("Back");
        teacherInputDto.setComents("No comments");
    }

    public static void comprobarTeacher(TeacherInputDto teacherInputDto, SimpleTeacherOutputDto teacherOutputDto) {
        Assertions.assertEquals(teacherInputDto.getBranch(), teacherOutputDto.getBranch());
        Assertions.assertEquals(teacherInputDto.getComents(), teacherOutputDto.getComents());
    }
}
