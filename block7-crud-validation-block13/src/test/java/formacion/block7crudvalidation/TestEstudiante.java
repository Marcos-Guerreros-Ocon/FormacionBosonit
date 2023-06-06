package formacion.block7crudvalidation;

import formacion.block7crudvalidation.asignatura.application.AsignaturaService;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.application.PersonService;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.student.application.StudentService;
import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import formacion.block7crudvalidation.teacher.application.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static formacion.block7crudvalidation.AgregarPersona.agregarPersona;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestEstudiante {

    @Autowired
    PersonService personService;

    @Autowired
    StudentService studentService;

    @Autowired
    AsignaturaService asignaturaService;


    @Test
    void addStudent() throws UnprocessableEntityException, EntityNotFoundException {

        AsignaturaInputDto asignaturaInputDto = new AsignaturaInputDto();

        asignaturaInputDto.setInitial_date(Date.from(Instant.now()));
        asignaturaInputDto.setAsignatura("Testing");
        asignaturaInputDto.setComents("Testing");

        asignaturaService.addAsignatura(asignaturaInputDto);

        PersonInputDto inserPerson = new PersonInputDto();
        agregarPersona(inserPerson);

        int idPerson = personService.addPerson(inserPerson).getId();
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_persona(idPerson);
        studentInputDto.setComents("Comment");
        studentInputDto.setBranch("Back");
        studentInputDto.setNum_hours_week(200);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        studentInputDto.setId_asignaturas(list);

        SimpleStudentOutputDto studentOutputDto = studentService.addStudent(studentInputDto);

        Assertions.assertEquals(studentInputDto.getComents(), studentOutputDto.getComents());
        Assertions.assertEquals(studentInputDto.getBranch(), studentOutputDto.getBranch());
        Assertions.assertEquals(studentInputDto.getNum_hours_week(), studentOutputDto.getNum_hours_week());

        studentService.deleteStudentById(1);
        personService.deletePersonById(idPerson);


    }

    @Test
    void addStudentWithoutPerson(){
        StudentInputDto studentInputDto = new StudentInputDto();
        Assertions.assertThrows(UnprocessableEntityException.class,()->{
            studentService.addStudent(studentInputDto);
        });
    }
}
