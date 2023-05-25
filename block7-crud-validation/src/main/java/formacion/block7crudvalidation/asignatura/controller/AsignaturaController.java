package formacion.block7crudvalidation.asignatura.controller;

import formacion.block7crudvalidation.asignatura.application.AsignaturaService;
import formacion.block7crudvalidation.asignatura.application.AsignaturaServiceImpl;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.SimpleAsignaturaOutputDto;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    AsignaturaService asignaturaService;

    @GetMapping
    public Iterable<AsignaturaOutputDto> getAllAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleAsignaturaOutputDto> getAsignaturaById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {

        try {
            if (outputType.equals("full")) return ResponseEntity.ok().body(asignaturaService.getFullAsignaturaById(id));

            if (!outputType.equals("simple")) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));

        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }


    }
    @GetMapping("/estudiante/{id}")
    public Iterable<SimpleAsignaturaOutputDto> getAllAsignaturasByStudent(@PathVariable int id){
        try {
            return asignaturaService.getAllAsignaturasByStudent(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping
    public ResponseEntity<SimpleAsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto) {
        try {
            URI location = URI.create("/asignatura");
            return ResponseEntity.created(location).body(asignaturaService.addAsignatura(asignaturaInputDto));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleAsignaturaOutputDto> updateAsignatura(@PathVariable int id, @RequestBody AsignaturaInputDto asignaturaInputDto) {
        try {
            return ResponseEntity.ok().body(asignaturaService.updateAsignaturaById(id, asignaturaInputDto));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsignatura(@PathVariable int id) {
        try {
            asignaturaService.deleteAsignaturaById(id);
            return ResponseEntity.ok().body("La asignatura con el id: " + id + " ha sido borrada");
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
