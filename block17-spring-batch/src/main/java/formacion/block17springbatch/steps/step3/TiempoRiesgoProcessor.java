package formacion.block17springbatch.steps.step3;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import formacion.block17springbatch.tiemporiesgo.TiempoRiesgoDTO;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoProcessor implements ItemProcessor<TiempoRiesgoDTO, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(TiempoRiesgoDTO t) throws Exception {
        TiempoRiesgo tiempo = new TiempoRiesgo();
        tiempo.setNumeroAno(t.getNumeroAno());
        tiempo.setNumeroMes(t.getNumeroMes());
        tiempo.setCiudad(t.getCiudad());
        tiempo.setNumeroTemperaturas(t.getNumeroTemperaturas());
        tiempo.setTemperaturaMedia(t.getTemperaturaMedia());
        if(t.getTemperaturaMedia()>=36)
            tiempo.setRiesgo("HIGH");
        else if (t.getTemperaturaMedia()<36 && t.getTemperaturaMedia()>32)
            tiempo.setRiesgo("MEDIUM");
        else
            tiempo.setRiesgo("LOW");
        return tiempo;
    }
}
