package formacion.block17springbatch.steps.step2;


import formacion.block17springbatch.tiempo.Tiempo;
import formacion.block17springbatch.tiempo.TiempoOutputDTO;
import org.springframework.batch.item.ItemProcessor;

public class TiempoErrorProcessor implements ItemProcessor<Tiempo, TiempoOutputDTO> {
    @Override
    public TiempoOutputDTO process(Tiempo tiempo) throws Exception {
        if(tiempo.getTemperatura()>50 || tiempo.getTemperatura()<-20) {
            return new TiempoOutputDTO(tiempo);
        }else
            return null;
    }
}
