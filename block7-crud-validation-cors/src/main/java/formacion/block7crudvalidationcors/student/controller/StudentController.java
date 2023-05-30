package formacion.block7crudvalidationcors.student.controller;


import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.student.application.StudentService;
import formacion.block7crudvalidationcors.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidationcors.student.controller.dto.StudentInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/{id}")
    public ResponseEntity<SimpleStudentOutputDto> getStudentById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            if (outputType.equals("full"))
                return ResponseEntity.ok().body(studentService.getStudentById(id));

            return ResponseEntity.ok().body(studentService.getSimpleStudentById(id));

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public Iterable<SimpleStudentOutputDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping
    public ResponseEntity<SimpleStudentOutputDto> addStudent(@RequestBody StudentInputDto student) {
        try {
            URI location = URI.create("/student");
            return ResponseEntity.created(location).body(studentService.addStudent(student));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleStudentOutputDto> editStudent(@PathVariable int id, @RequestBody StudentInputDto student) {

        try {
            return ResponseEntity.ok().body(studentService.updateStudent(id, student));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnprocessableEntityException e) {
            throw new RuntimeException(e);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("El estudiante con id: " + id + " ha sido borrado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/addAsignaturas")
    public ResponseEntity<SimpleStudentOutputDto> addAsignaturas(@PathVariable int id, @RequestBody List<Integer> idAsignaturas) {
        try {
            return ResponseEntity.ok().body(studentService.addAsignaturas(id, idAsignaturas));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/removeAsignaturas")
    public ResponseEntity<SimpleStudentOutputDto> removeAsignaturas(@PathVariable int id, @RequestBody List<Integer> idAsignaturas) {
        try {
            return ResponseEntity.ok().body(studentService.removeAsignaturas(id, idAsignaturas));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

}
