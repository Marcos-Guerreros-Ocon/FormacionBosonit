package formacion.block17springbatch.steps.step2;


import formacion.block17springbatch.domain.Tiempo;
import formacion.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;


public class TiempoRiesgoProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {

    private static final int MIN_RIESGO_ALTO = 36;
    private static final int MIN_RIESGO_MEDIO = 32;

    @Override
    public TiempoRiesgo process(Tiempo tiempo){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo.getFecha());

        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setLocalidad(tiempo.getLocalidad());
        tiempoRiesgo.setMes(calendar.get(Calendar.MONTH));
        tiempoRiesgo.setAno(calendar.get(Calendar.YEAR));
        tiempoRiesgo.setTemperatura(tiempo.getTemperatura());

        int temperatura = tiempo.getTemperatura();
        if (temperatura>=MIN_RIESGO_ALTO){
            tiempoRiesgo.setRiesgo("HIGH");
        } else if (temperatura>=MIN_RIESGO_MEDIO) {
            tiempoRiesgo.setRiesgo("NORMAL");
        }else{
            tiempoRiesgo.setRiesgo("LOW");
        }

        tiempo.setIdTiempo(tiempo.getIdTiempo());
        tiempoRiesgo.setTiempo(tiempo);
        return tiempoRiesgo;
    }
}
