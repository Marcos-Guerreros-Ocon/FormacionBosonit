package formacion.block6personcontrollers.controllers;

import formacion.block6personcontrollers.models.PersonModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    private PersonModel personBean1;
    private PersonModel personBean2;
    private PersonModel personBean3;

    public BeanController(@Qualifier("bean1") PersonModel person1,@Qualifier("bean2") PersonModel person2,@Qualifier("bean3") PersonModel person3) {
        personBean1 = person1;
        personBean2 = person2;
        personBean3 = person3;

    }


    @GetMapping("controlador/bean/{bean}")
    public PersonModel getPersonBean(@PathVariable String bean) {
       switch (bean){
           case "bean1":
               return personBean1;
           case "bean2":
               return personBean2;
           case "bean3":
               return personBean3;
           default:
               return new PersonModel();
       }
    }
}
