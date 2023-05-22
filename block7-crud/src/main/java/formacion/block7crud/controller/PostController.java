package formacion.block7crud.controller;

import formacion.block7crud.application.PersonServiceImpl;
import formacion.block7crud.controller.dto.PersonInputDto;
import formacion.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PostController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        URI location = URI.create("/person");

        return ResponseEntity.created(location).body(personService.addPerson(person));

    }
}
