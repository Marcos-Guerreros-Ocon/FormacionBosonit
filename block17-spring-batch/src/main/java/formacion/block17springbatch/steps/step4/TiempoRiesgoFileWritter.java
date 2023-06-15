package formacion.block17springbatch.steps.step4;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;


public class TiempoRiesgoFileWritter extends FlatFileItemWriter<TiempoRiesgo> {
    public TiempoRiesgoFileWritter(){
        setResource(new FileSystemResource("src/main/resources/out.csv"));
        setAppendAllowed(false);
        setLineAggregator(new DelimitedLineAggregator<TiempoRiesgo>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<TiempoRiesgo>() {
                    {
                        setNames(new String[]{"ciudad", "numeroMes", "numeroAno","numeroTemperaturas","temperaturaMedia","riesgo"});
                    }
                });
            }
        });
    }
}
