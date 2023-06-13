package formacion.block7crudvalidation;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.application.PersonService;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static formacion.block7crudvalidation.AgregarPersona.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPersona {
    @Autowired
    private PersonService personService;

    @AfterEach
    void reiniciarTodo() {
        Iterable<PersonOutputDto> list = personService.getAllPerson();

        list.forEach(per -> {
            try {
                personService.deletePersonById(per.getId());
            } catch (EntityNotFoundException e) {

            }
        });
    }

    @Test
    @DisplayName("Crear persona correcta")
    void crearPersona() throws Exception {
        PersonInputDto personInputDto = new PersonInputDto();
        agregarPersona(personInputDto);
        comprobarPersona(personInputDto, personService.addPerson(personInputDto));
    }

    @Test
    @DisplayName("Crear persona sin datos")
    void crearPersonaSinDatos() {
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setUsuario("NombreExtremadamenteLargo");
        Assertions.assertThrows(UnprocessableEntityException.class, () -> {
            personService.addPerson(personInputDto);
        });
    }

    @Test
    @DisplayName("Crear persona sin datos")
    void crearPersonaConUsernameLargo() {
        PersonInputDto personInputDto = new PersonInputDto();
        Assertions.assertThrows(UnprocessableEntityException.class, () -> {
            personService.addPerson(personInputDto);
        });
    }

    @Test
    void getPersona() throws EntityNotFoundException, UnprocessableEntityException {
        PersonInputDto personInputDto = new PersonInputDto();
        agregarPersona(personInputDto);
        int idPersona = personService.addPerson(personInputDto).getId();
        comprobarPersona(personInputDto, personService.getPersonById(idPersona));
    }

    @Test
    void getPersonaByIdNotFound() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            personService.getPersonById(12);
        });
    }

    @Test
    void updatePersona() throws UnprocessableEntityException, EntityNotFoundException {
        PersonInputDto personInputDto = new PersonInputDto();
        agregarPersona(personInputDto);
        int id = personService.addPerson(personInputDto).getId();
        modificarPersona(personInputDto);
        comprobarPersona(personInputDto, personService.updatePerson(id, personInputDto));
    }

    @Test
    void updatePersonaConIdNoEncontrado() {
        PersonInputDto personInputDto = new PersonInputDto();
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            personService.updatePerson(12, personInputDto);
        });
    }

    @Test
    void updatePersonaSinDatos() throws UnprocessableEntityException {
        PersonInputDto personInputDto = new PersonInputDto();
        agregarPersona(personInputDto);
        int id = personService.addPerson(personInputDto).getId();
        PersonInputDto personInputDto2 = new PersonInputDto();
        Assertions.assertThrows(UnprocessableEntityException.class, () -> {
            personService.updatePerson(id, personInputDto2);
        });
    }

    @Test
    void borrarPersona() throws EntityNotFoundException, UnprocessableEntityException {
        PersonInputDto personInputDto = new PersonInputDto();
        agregarPersona(personInputDto);
        int id = personService.addPerson(personInputDto).getId();
        personService.deletePersonById(id);
    }

    @Test
    void deletePersonaThrowException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            personService.deletePersonById(1);
        });
    }
}