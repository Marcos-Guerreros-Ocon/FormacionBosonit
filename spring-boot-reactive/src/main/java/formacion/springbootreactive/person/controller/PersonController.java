package formacion.springbootreactive.person.controller;


import formacion.springbootreactive.exception.UnprocessableEntityException;
import formacion.springbootreactive.person.application.PersonService;
import formacion.springbootreactive.person.controller.dto.PersonInputDto;
import formacion.springbootreactive.person.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public Mono<PersonOutputDto> addPerson(@RequestBody PersonInputDto personInputDto) {

        try {
            return personService.addPerson(personInputDto);
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return Mono.empty();
        }

    }

    @GetMapping("/{id}")
    public Mono<PersonOutputDto> getPersonId(@PathVariable("id") String id) {
        return personService.getPersonById(id);

    }


    @GetMapping
    public Flux<PersonOutputDto> getPeoplePage() {
        return personService.getAllPeople();
    }

    @PutMapping("{id}")
    public Mono<PersonOutputDto> updatePerson(@PathVariable("id") String id, @RequestBody PersonInputDto personInputDto) {
        return personService.updatePersonById(id, personInputDto);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deletePerson(@PathVariable String id) {
        return personService.deletePersonById(id);
    }

}
