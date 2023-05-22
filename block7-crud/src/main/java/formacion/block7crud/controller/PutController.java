package formacion.block7crud.controller;

import formacion.block7crud.application.PersonServiceImpl;
import formacion.block7crud.controller.dto.PersonInputDto;
import formacion.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PutController {

    @Autowired
    PersonServiceImpl personService;

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> editPerson(@PathVariable int id, @RequestBody PersonInputDto person) {
        try {
            personService.getPersonById(id);
            return ResponseEntity.ok().body(personService.updatePerson(id, person));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
