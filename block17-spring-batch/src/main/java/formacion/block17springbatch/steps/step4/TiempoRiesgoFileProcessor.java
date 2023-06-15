package formacion.block17springbatch.steps.step4;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoFileProcessor implements ItemProcessor<TiempoRiesgo, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(TiempoRiesgo tiempoRiesgo) throws Exception {
        return tiempoRiesgo;
    }
}
