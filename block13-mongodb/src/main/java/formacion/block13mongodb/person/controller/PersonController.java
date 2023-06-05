package formacion.block13mongodb.person.controller;


import formacion.block13mongodb.exception.EntityNotFoundException;
import formacion.block13mongodb.exception.UnprocessableEntityException;
import formacion.block13mongodb.person.application.PersonService;
import formacion.block13mongodb.person.controller.dto.PersonInputDto;
import formacion.block13mongodb.person.controller.dto.PersonOutputDto;
import formacion.block13mongodb.person.domain.Person;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto personInputDto) {
        try {
            return ResponseEntity.ok().body(personService.addPerson(personInputDto));
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<PersonOutputDto> getPeoplePage() {
        return personService.getAllPeople();

    }
    @GetMapping("/page/{page}")
    public List<PersonOutputDto> getPeoplePage(@PathVariable int page) {
        return personService.getPeople(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonId(@PathVariable("id") String id) {

        try {
            return ResponseEntity.ok(personService.getPersonById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@PathVariable("id") String id, @RequestBody PersonInputDto personInputDto) {
        try {
            return ResponseEntity.ok().body(personService.updatePerson(id, personInputDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id) {
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Persona con id: " + id + " ha sido borrada");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}