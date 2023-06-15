package formacion.block17springbatch.steps.step1;


import formacion.block17springbatch.tiempo.Tiempo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoProcessor implements ItemProcessor<Tiempo,Tiempo> {
    @Override
    public Tiempo process(Tiempo tiempo) throws Exception {
        if(tiempo.getTemperatura()>50 || tiempo.getTemperatura()<-20) {
            throw new IllegalArgumentException();
        }else
            return tiempo;
    }
}
