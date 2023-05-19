package formacion.block5profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class Prueba {
    @Autowired
    private Environment environment;

    public Prueba() {

    }
}
