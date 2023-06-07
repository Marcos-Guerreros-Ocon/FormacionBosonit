package formacion.block7crudvalidation;

import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.application.PersonService;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients
public class Block7CrudValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block7CrudValidationApplication.class, args);
    }

    @Autowired
    PersonService personService;

    @PostConstruct
    public void crearUsers() throws UnprocessableEntityException {
        PersonInputDto personAdmin = new PersonInputDto();
        personAdmin.setUsuario("marcos");
        personAdmin.setPassword("1234");
        personAdmin.setName("Marcos");
        personAdmin.setSurname("Guerreros");
        personAdmin.setCompanyEmail("marcos@bosonit.com");
        personAdmin.setPersonalEmail("marcos@correo.com");
        personAdmin.setCity("Logroño");
        personAdmin.setCreatedDate(Date.from(Instant.now()));
        personAdmin.setAdmin(true);

        personService.addPerson(personAdmin);

        PersonInputDto personUser = new PersonInputDto();
        personUser.setUsuario("alejandro");
        personUser.setPassword("$2a$12$xy9C8Vkj8gUG.S1SnthqeO6w2e24PU.zmSNk3nu1OLwb1nozV4RuO");
        personUser.setName("Alejandro");
        personUser.setSurname("Ojeda");
        personUser.setCompanyEmail("alejandro@bosonit.com");
        personUser.setPersonalEmail("alejandro@correo.com");
        personUser.setCity("Logroño");
        personUser.setCreatedDate(Date.from(Instant.now()));
        personUser.setAdmin(false);

        personService.addPerson(personUser);


    }

}
