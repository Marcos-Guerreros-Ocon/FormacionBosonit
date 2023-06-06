package formacion.block7crudvalidation;

import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class AgregarPersona {

    public static void agregarPersona(PersonInputDto personInputDto) {
        try {
            personInputDto.setUsuario("Marcos");
            personInputDto.setPassword("1234");
            personInputDto.setName("Marcos");
            personInputDto.setSurname("Guerreros");
            personInputDto.setCompanyEmail("marcos@bosonit.com");
            personInputDto.setPersonalEmail("marcos@gmail.com");
            personInputDto.setCity("Logroño");
            personInputDto.setActive(true);

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date d = format.parse("05-06-2023");

            personInputDto.setCreatedDate(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void modificarPersona(PersonInputDto personInputDto) {
        try {
            personInputDto.setUsuario("Alejandro");
            personInputDto.setPassword("22222222");
            personInputDto.setName("Alejandro");
            personInputDto.setSurname("Alejandro");
            personInputDto.setCompanyEmail("prueba@bosonit.com");
            personInputDto.setPersonalEmail("prueba@gmail.com");
            personInputDto.setCity("Prueba");
            personInputDto.setActive(false);

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date d = format.parse("05-06-2023");

            personInputDto.setCreatedDate(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void comprobarPersona(PersonInputDto personInputDto, PersonOutputDto personOutputDto){
        Assertions.assertEquals(personInputDto.getUsuario(), personOutputDto.getUsuario());
        Assertions.assertEquals(personInputDto.getName(), personOutputDto.getName());
        Assertions.assertEquals(personInputDto.getSurname(), personOutputDto.getSurname());
        Assertions.assertEquals(personInputDto.getCompanyEmail(), personOutputDto.getCompanyEmail());
        Assertions.assertEquals(personInputDto.getPersonalEmail(), personOutputDto.getPersonalEmail());
        Assertions.assertEquals(personInputDto.getCity(), personOutputDto.getCity());
        Assertions.assertEquals(personInputDto.isActive(), personOutputDto.isActive());
    }

}
