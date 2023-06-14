package formacion.block17springbatch.steps.step3;


import formacion.block17springbatch.domain.Tiempo;
import formacion.block17springbatch.domain.TiempoError;
import org.springframework.batch.item.ItemProcessor;

public class TiempoErrorProcessor implements ItemProcessor<Tiempo, TiempoError> {
    @Override
    public TiempoError process(Tiempo tiempo) throws Exception {
        TiempoError tiempoError = new TiempoError();
        tiempoError.setLocalidad(tiempo.getLocalidad());
        tiempoError.setFecha(tiempo.getFecha());
        tiempoError.setTemperatura(tiempo.getTemperatura());

        return tiempoError;

    }
}