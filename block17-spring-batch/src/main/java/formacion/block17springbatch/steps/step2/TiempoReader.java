package formacion.block17springbatch.steps.step2;


import formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;


/***
 * Clase para obtener los tiempos que hemos leido en el csv
 */
public class TiempoReader extends JpaCursorItemReader<Tiempo> {
    public TiempoReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM Tiempo t");
    }

}
