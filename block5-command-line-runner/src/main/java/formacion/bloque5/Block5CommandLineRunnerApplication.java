package formacion.bloque5;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Block5CommandLineRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
    }

    @Bean
    CommandLineRunner clase2() {
        return p -> System.out.println("Hola desde clase secundaria");
    }

    @Bean
    CommandLineRunner clase3() {
        return p-> System.out.println("Soy la tercera clase" );
    }
}
