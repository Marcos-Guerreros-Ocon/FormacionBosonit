package formacion.block17springbatch.steps.step2;



import formacion.block17springbatch.domain.TiempoRiesgo;
import formacion.block17springbatch.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoRiesgoWriterBD implements ItemWriter<TiempoRiesgo> {
    @Autowired
    TiempoRiesgoRepository tiempoRiesgoRepository;

    @Override
    public void write(List<? extends TiempoRiesgo> list) throws Exception {
        tiempoRiesgoRepository.saveAll(list);
    }
}
