package formacion.block17springbatch.config;


import formacion.block17springbatch.steps.step1.*;
import formacion.block17springbatch.steps.step2.*;
import formacion.block17springbatch.steps.step3.*;
import formacion.block17springbatch.steps.step4.*;
import formacion.block17springbatch.tiempo.Tiempo;
import formacion.block17springbatch.tiempo.TiempoOutputDTO;
import formacion.block17springbatch.tiempo.repository.TiempoRepository;
import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import formacion.block17springbatch.tiemporiesgo.TiempoRiesgoDTO;
import formacion.block17springbatch.tiemporiesgo.repository.TiempoRiesgoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    TiempoRepository tiempoRepository;
    @Autowired
    TiempoRiesgoRepository tiempoRiesgoRepository;
    @Autowired
    DataSource dataSource;

    // INICIO 1º Step
    @Bean
    TiempoReader tiempoReader() {
        return new TiempoReader();
    }

    @Bean
    TiempoWritter tiempoWritter() {
        return new TiempoWritter(tiempoRepository);
    }

    @Bean
    TiempoProcessor tiempoProcessor() {
        return new TiempoProcessor();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Tiempo, Tiempo>chunk(100)
                .faultTolerant()
                .skipLimit(5)
                .skip(IllegalArgumentException.class)
                .reader(tiempoReader())
                .processor(tiempoProcessor())
                .writer(tiempoWritter())
                .build();
    }
    // FIN 1º Step

    // INICIO 2º Step
    @Bean
    TiempoErrorProcessor tiempoErrorProcessor(){
        return new TiempoErrorProcessor();
    }
    @Bean

    TiempoErrorWritter tiempoErrorWritter(){
        return new TiempoErrorWritter();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Tiempo, TiempoOutputDTO>chunk(100)
                .reader(tiempoReader())
                .processor(tiempoErrorProcessor())
                .writer(tiempoErrorWritter())
                .build();
    }
    // FIN 2º Step

    // INICIO 3º Step
    @Bean
    TiempoRiesgoReader tiempoRiesgoReader(){
        return new TiempoRiesgoReader(dataSource);
    }
    @Bean
    TiempoRiesgoProcessor tiempoRiesgoProcessor(){
        return new TiempoRiesgoProcessor();
    }
    @Bean
    TiempoRiesgoWritter tiempoRiesgoWritter(){
        return new TiempoRiesgoWritter(tiempoRiesgoRepository);
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .<TiempoRiesgoDTO, TiempoRiesgo>chunk(100)
                .reader(tiempoRiesgoReader())
                .processor(tiempoRiesgoProcessor())
                .writer(tiempoRiesgoWritter())
                .build();
    }
    // FIN 3º Step

    // INICIO 4º Step
    @Bean
    TiempoRiesgoDatabaseReader tiempoRiesgoDatabaseReader(){
        return new TiempoRiesgoDatabaseReader(dataSource);
    }
    @Bean
    TiempoRiesgoFileProcessor tiempoRiesgoFileProcessor(){
        return new TiempoRiesgoFileProcessor();
    }
    @Bean
    TiempoRiesgoFileWritter tiempoRiesgoFileWritter(){
        return new TiempoRiesgoFileWritter();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .<TiempoRiesgo, TiempoRiesgo>chunk(100)
                .reader(tiempoRiesgoDatabaseReader())
                .processor(tiempoRiesgoFileProcessor())
                .writer(tiempoRiesgoFileWritter())
                .build();
    }
    // FIN 4º Step

    // INICIO Job
    @Bean
    public Job proccesData() {
        return jobBuilderFactory.get("proccesData")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .end()
                .build();
    }
    // FIN Job

}