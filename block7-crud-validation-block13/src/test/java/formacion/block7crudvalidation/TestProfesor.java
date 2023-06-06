package formacion.block7crudvalidation;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.application.PersonService;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.teacher.application.TeacherService;
import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static formacion.block7crudvalidation.AgregarPersona.agregarPersona;
import static formacion.block7crudvalidation.AgregarProfesor.agregarProfesor;
import static formacion.block7crudvalidation.AgregarProfesor.comprobarTeacher;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestProfesor {

    @Autowired
    TeacherService teacherService;

    @Autowired
    PersonService personService;

    @AfterEach
    void reiniciarTodo() {
        Iterable<SimpleTeacherOutputDto> listTeacher = teacherService.getAllTeacher();

        listTeacher.forEach(per -> {
            try {
                teacherService.deleteTeacherById(per.getIdProfesor());
            } catch (EntityNotFoundException e) {

            }
        });

        Iterable<PersonOutputDto> list = personService.getAllPerson();

        list.forEach(per -> {
            try {
                personService.deletePersonById(per.getId());
            } catch (EntityNotFoundException e) {

            }
        });
    }

    @Test
    void crearProfesor() throws UnprocessableEntityException, EntityNotFoundException {
        PersonInputDto personInputDto = new PersonInputDto();
        TeacherInputDto teacherInputDto = new TeacherInputDto();

        agregarPersona(personInputDto);
        int id = personService.addPerson(personInputDto).getId();

        agregarProfesor(id, teacherInputDto);
        comprobarTeacher(teacherInputDto, teacherService.addTeacher(teacherInputDto));
    }

    @Test
    void crearProfesorSinPersona() {
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        agregarProfesor(0, teacherInputDto);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            teacherService.addTeacher(teacherInputDto);
        });
    }

    @Test
    void crearProfesorSinCamposObligatorios() {
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        Assertions.assertThrows(UnprocessableEntityException.class, () -> {
            teacherService.addTeacher(teacherInputDto);
        });
    }

    @Test
    void modificarProfesor() throws UnprocessableEntityException, EntityNotFoundException {
        PersonInputDto personInputDto = new PersonInputDto();
        TeacherInputDto teacherInputDto = new TeacherInputDto();

        agregarPersona(personInputDto);
        int id = personService.addPerson(personInputDto).getId();

        agregarProfesor(id, teacherInputDto);
        int idProfesor = teacherService.addTeacher(teacherInputDto).getIdProfesor();
        TeacherInputDto teacherInputDto1 = new TeacherInputDto();

        teacherInputDto1.setId_persona(id);
        teacherInputDto1.setBranch("Cambiado");
        teacherInputDto1.setComents("Cambiado");
        comprobarTeacher(teacherInputDto1, teacherService.updateTeacher(idProfesor, teacherInputDto1));

    }

    @Test
    void borrarProfesor() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            teacherService.deleteTeacherById(1);
        });
    }

    @Test
    void obtenerProfesoreById() throws EntityNotFoundException, UnprocessableEntityException {

        TeacherInputDto insertTeacher = new TeacherInputDto();
        PersonInputDto insertPerson = new PersonInputDto();

        agregarPersona(insertPerson);
        int idPersona = personService.addPerson(insertPerson).getId();

        agregarProfesor(idPersona, insertTeacher);
        int idProfesor = teacherService.addTeacher(insertTeacher).getIdProfesor();

        SimpleTeacherOutputDto teacher = teacherService.getTeacherById(idProfesor);

        SimpleTeacherOutputDto teacherOutputDto = new SimpleTeacherOutputDto();
        teacherOutputDto.setIdProfesor(idProfesor);
        teacherOutputDto.setBranch(insertTeacher.getBranch());
        teacherOutputDto.setComents(insertTeacher.getComents());


        Assertions.assertEquals(teacher.getIdProfesor(), teacherOutputDto.getIdProfesor());
        Assertions.assertEquals(teacher.getBranch(), teacherOutputDto.getBranch());
        Assertions.assertEquals(teacher.getComents(), teacherOutputDto.getComents());

    }

    @Test
    void obtenerProfesorByIdNotFound() {
        Assertions.assertThrows(EntityNotFoundException.class, () ->
                teacherService.getTeacherById(222));
    }
}
