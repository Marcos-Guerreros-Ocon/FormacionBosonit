package formacion.block7crudvalidationcors.person.controller;


import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.feign.MyFeign;
import formacion.block7crudvalidationcors.person.application.PersonService;
import formacion.block7crudvalidationcors.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidationcors.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.teacher.controller.dto.SimpleTeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    MyFeign feign;

    @GetMapping("/getall")
    public Iterable<PersonOutputDto> getAllPeople() {
        return personService.getAllPerson();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {


        try {
            if (outputType.equals("full")) {
                return ResponseEntity.ok().body(personService.getFullPersonById(id));
            }
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/person/nombre/{name}")
    public Iterable<PersonOutputDto> getAllPeopleByName(@PathVariable String name) {

        return personService.getPeopleByName(name);
    }

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        URI location = URI.create("/person");
        try {
            return ResponseEntity.created(location).body(personService.addPerson(person));
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/addperson")
    public ResponseEntity<PersonOutputDto> addPersonCor(@RequestBody PersonInputDto person) {
        URI location = URI.create("/person");
        try {
            return ResponseEntity.created(location).body(personService.addPerson(person));
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@PathVariable int id, @RequestBody PersonInputDto person) {
        try {
            return ResponseEntity.ok().body(personService.updatePerson(id, person));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> removePerson(@PathVariable int id) {
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("La persona con id: " + id + " ha sido borrado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profesor/{idProfesor}")
    public SimpleTeacherOutputDto getProfesorFeign(@PathVariable int idProfesor) {
      return feign.getTeacherById(idProfesor);
    }

}