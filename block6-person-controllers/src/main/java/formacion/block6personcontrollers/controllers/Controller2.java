package formacion.block6personcontrollers.controllers;

import formacion.block6personcontrollers.Components.CityComponent;
import formacion.block6personcontrollers.models.CityModel;
import formacion.block6personcontrollers.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller2 {

    @Autowired
    Controller1 controller1;
    @Autowired
    CityComponent cityComponent;

    @GetMapping("/controlador2/getPersona")
    public PersonModel getPerson() {
        PersonModel person = controller1.getPersonModel();

        person.setAge(person.getAge() * 2);

        return person;
    }

    @GetMapping("/controlador2/getCiudades")
    public List<CityModel> getCities() {
        return cityComponent.getCities();
    }
}
