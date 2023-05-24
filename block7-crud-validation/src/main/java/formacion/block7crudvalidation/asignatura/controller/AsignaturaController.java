package formacion.block7crudvalidation.asignatura.controller;

import formacion.block7crudvalidation.asignatura.application.AsignaturaServiceImpl;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    AsignaturaServiceImpl asignaturaService;
    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto){
        try {
            URI location = URI.create("/asignatura");
            return ResponseEntity.created(location).body(asignaturaService.añadirAsignatura(asignaturaInputDto));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
