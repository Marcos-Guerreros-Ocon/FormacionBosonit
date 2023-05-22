package formacion.block6personcontrollers.controllers;


import formacion.block6personcontrollers.Components.CityComponent;
import formacion.block6personcontrollers.models.CityModel;
import formacion.block6personcontrollers.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller1 {

    private PersonModel personModel;

    @Autowired
    private CityComponent cityComponent;

    @GetMapping("/controlador1/addPersona")
    public PersonModel addPerson(@RequestHeader String name, @RequestHeader String city, @RequestHeader int age) {
        personModel = new PersonModel(name, city, age);
        return personModel;
    }

    @PostMapping("/controlador1/addCiudad")
    public void addCiudad(@RequestBody CityModel city){
        cityComponent.addCity(city);
    }
    public PersonModel getPersonModel() {

        return personModel;
    }
}
