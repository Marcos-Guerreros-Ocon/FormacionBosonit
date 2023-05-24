package formacion.block7crudvalidation.student.controller;


import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.student.application.StudentServiceImpl;
import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto student) {
        try {
            URI location = URI.create("/student");
            return ResponseEntity.created(location).body(studentService.addStudent(student));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleStudentOutputDto> getStudentById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            if (outputType.equals("simple"))
                return ResponseEntity.ok().body(studentService.getSimpleStudentById(id));

            return ResponseEntity.ok().body(studentService.getStudentById(id));

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public Iterable<SimpleStudentOutputDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PutMapping
    public ResponseEntity<SimpleStudentOutputDto> editStudent(@PathVariable int id, @RequestBody StudentInputDto student){
        getStudentById(id,"simple");

return null;
    }
}
