package formacion.block7crudvalidation;

import com.github.javafaker.Faker;
import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients
public class Block7CrudValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block7CrudValidationApplication.class, args);
    }

    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void populateDb() {
        LocalDateTime localDateTime = LocalDateTime.parse("2019-01-15T13:15:30");
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        localDateTime = LocalDateTime.now();
        instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Faker f = new Faker();

        String usarName, lastName, companyEmail, personalEmail, city;
        Date date1;
        for (int i = 0; i < 20; i++) {
            usarName = f.name().firstName();
            lastName = f.name().lastName();
            companyEmail = f.internet().emailAddress();
            personalEmail = f.internet().emailAddress();
            city = f.address().city();
            date1 = f.date().between(date, Date.from(instant));
            while (usarName.length() < 6 || usarName.length() > 10) usarName = f.name().firstName();
            personRepository.save(new Person(0, usarName, "1234", usarName, lastName, companyEmail, personalEmail, city, true, date1, null, null, null, null));
        }

    }

}
