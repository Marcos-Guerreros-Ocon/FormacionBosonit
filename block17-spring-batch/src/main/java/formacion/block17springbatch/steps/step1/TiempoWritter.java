package formacion.block17springbatch.steps.step1;


import formacion.block17springbatch.tiempo.Tiempo;
import formacion.block17springbatch.tiempo.repository.TiempoRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class TiempoWritter implements ItemWriter<Tiempo> {

    private TiempoRepository tiempoRepository;

    public TiempoWritter(TiempoRepository tiempoRepository) {
        this.tiempoRepository = tiempoRepository;
    }

    @Override
    public void write(List<? extends Tiempo> list) throws Exception {
        tiempoRepository.saveAll(list);

    }
}
