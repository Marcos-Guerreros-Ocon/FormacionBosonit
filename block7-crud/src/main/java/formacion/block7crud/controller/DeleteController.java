package formacion.block7crud.controller;

import formacion.block7crud.application.PersonServiceImpl;
import formacion.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class DeleteController {

    @Autowired
    PersonServiceImpl personService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id: " + id + " was deleted");
        }catch (Exception e){
            return ResponseEntity.notFound().build();

        }
    }
}
