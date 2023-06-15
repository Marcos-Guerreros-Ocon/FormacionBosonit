package formacion.block17springbatch.steps.step3;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import formacion.block17springbatch.tiemporiesgo.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class TiempoRiesgoWritter implements ItemWriter<TiempoRiesgo> {

    private TiempoRiesgoRepository tiempoRiesgoRepository;

    public TiempoRiesgoWritter(TiempoRiesgoRepository tiempoRiesgoRepository){
        this.tiempoRiesgoRepository=tiempoRiesgoRepository;
    }
    @Override
    public void write(List<? extends TiempoRiesgo> list) throws Exception {
        tiempoRiesgoRepository.saveAll(list);

    }
}
