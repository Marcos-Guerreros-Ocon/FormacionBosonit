package formacion.bloque5;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ClaseInicial {

    @PostConstruct
    private void saludo(){
        System.out.println("Hola desde clase inicial");
    }

}
