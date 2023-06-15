package formacion.block17springbatch.steps.step2;


import formacion.block17springbatch.tiempo.TiempoOutputDTO;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class TiempoErrorWritter extends FlatFileItemWriter<TiempoOutputDTO> {

    public TiempoErrorWritter(){
        setResource(new FileSystemResource("src/main/resources/errors.csv"));
        setAppendAllowed(false);
        setLineAggregator(new DelimitedLineAggregator<TiempoOutputDTO>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<TiempoOutputDTO>() {
                    {
                        setNames(new String[]{"ciudad", "fecha", "temperatura"});
                    }
                });
            }
        });
    }
}
