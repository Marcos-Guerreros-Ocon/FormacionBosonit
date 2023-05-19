package formacion.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Variables implements CommandLineRunner , EnvironmentAware {


    @Value("${greeting}")
    private String saludo;

    @Value("${my.number}")
    private int numero;

    private String newProperty;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("El valor de greeting es: " + saludo);
        System.out.println("El valor de my.number es: " + numero);
        System.out.println("El valor de new.property es:  " + newProperty);
    }

    @Override
    public void setEnvironment(Environment environment) {
        newProperty = environment.getProperty("new.property");
    }
}
