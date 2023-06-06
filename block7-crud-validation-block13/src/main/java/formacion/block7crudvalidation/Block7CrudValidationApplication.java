package formacion.block7crudvalidation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;


@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients
public class Block7CrudValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block7CrudValidationApplication.class, args);
    }



}
