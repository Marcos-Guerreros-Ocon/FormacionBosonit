package formacion.block6personcontrollers.config;

import formacion.block6personcontrollers.models.PersonModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("bean1")
    @Qualifier("bean1")
    public PersonModel getPersonBean1(){
        PersonModel personModel = new PersonModel();
        personModel.setName("Bean1");
        return  personModel;
    }

    @Bean
    @Qualifier("bean2")
    public PersonModel getPersonBean2(){
        PersonModel personModel = new PersonModel();
        personModel.setName("Bean2");
        return  personModel;
    }

    @Bean
    @Qualifier("bean3")
    public PersonModel getPersonBean3(){
        PersonModel personModel = new PersonModel();
        personModel.setName("Bean3");
        return  personModel;
    }
}
