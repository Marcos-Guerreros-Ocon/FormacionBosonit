package formacion.block17springbatch;

import formacion.block17springbatch.domain.Tiempo;
import formacion.block17springbatch.domain.TiempoError;
import formacion.block17springbatch.domain.TiempoRiesgo;
import formacion.block17springbatch.steps.step1.ReaderCsv;
import formacion.block17springbatch.steps.step1.TiempoWriterBD;
import formacion.block17springbatch.steps.step2.TiempoReader;
import formacion.block17springbatch.steps.step2.TiempoRiesgoProcessor;
import formacion.block17springbatch.steps.step2.TiempoRiesgoWriterBD;
import formacion.block17springbatch.steps.step3.TiempoErrorProcessor;
import formacion.block17springbatch.steps.step3.TiempoErrorReader;
import formacion.block17springbatch.steps.step3.TiempoErrorWriterCSV;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    // Inicio 1º Step
    // Leemos el csv y lo almacenamos en la base de datos
    @Bean
    public ReaderCsv tiempoCsvReader() {
        return new ReaderCsv();
    }

    @Bean
    public TiempoWriterBD tiempoWriter() {
        return new TiempoWriterBD();
    }

    @Bean
    public Step step1() {
        CompositeItemWriter<Tiempo> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoWriter()));
        return stepBuilderFactory.get("step1")
                .<Tiempo, Tiempo>chunk(5)
                .reader(tiempoCsvReader())
                .writer(compositeWriter)
                .build();
    }

    // Fin 1º Step

    // Inicio 2º Step
    // Leer los tiempos de BD y meterlos en la tabla TiempoRiesgo
    @Bean
    public TiempoReader tiempoDbReader() {
        return new TiempoReader(entityManagerFactory);
    }

    @Bean
    public TiempoRiesgoProcessor tiempoRiesgoProcessor() {
        return new TiempoRiesgoProcessor();
    }

    @Bean
    public TiempoRiesgoWriterBD tiempoRiesgoWriterBD() {
        return new TiempoRiesgoWriterBD();
    }


    @Bean
    public Step step2() {
        CompositeItemWriter<TiempoRiesgo> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoRiesgoWriterBD()));
        return stepBuilderFactory.get("step2")
                .<Tiempo, TiempoRiesgo>chunk(100)
                .faultTolerant()
                .skipLimit(5)
                .skip(IllegalArgumentException.class)
                .reader(tiempoDbReader())
                .processor(tiempoRiesgoProcessor())
                .writer(compositeWriter)

                .build();
    }

    // Fin 2º Step

    // Inicio 3º Step

    @Bean
    TiempoErrorReader tiempoErrorReader() {
        return new TiempoErrorReader(entityManagerFactory);
    }

    @Bean
    TiempoErrorWriterCSV tiempoErrorWriterCSV() {
        return new TiempoErrorWriterCSV();
    }

    @Bean
    TiempoErrorProcessor tiempoErrorProcessor() {
        return new TiempoErrorProcessor();
    }

    @Bean
    public Step step3(){
        CompositeItemWriter<TiempoError> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(tiempoErrorWriterCSV()));

        return stepBuilderFactory
                .get("step3")
                .<Tiempo, TiempoError>chunk(100)
                .reader(tiempoErrorReader())
                .processor(tiempoErrorProcessor())
                .writer(compositeItemWriter)
                .build();
    }
    // Fin 3º Step

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }
}