package formacion.block6simplecontrollers.controllers;

import formacion.block6simplecontrollers.models.PersonModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{name}")
    public String saludarUser(@PathVariable String name){
        return "Hola " + name;
    }

    @PostMapping("/useradd/")
    public PersonModel userAdd(@RequestBody PersonModel person){
        return  person;
    }

}
