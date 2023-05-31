package formacion.block7crudvalidation.teacher.controller;

import formacion.block7crudvalidation.exception.*;
import formacion.block7crudvalidation.teacher.application.TeacherService;
import formacion.block7crudvalidation.teacher.controller.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    @GetMapping
    public Iterable<SimpleTeacherOutputDto> getAllTeachers() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleTeacherOutputDto> getTeacherById(@PathVariable int id) {
        try {

            return ResponseEntity.ok().body(teacherService.getTeacherById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SimpleTeacherOutputDto> addTeacher(@RequestBody TeacherInputDto teacher) {

        try {
            URI location = URI.create("/teacher");
            return ResponseEntity.created(location).body(teacherService.addTeacher(teacher));
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleTeacherOutputDto> updateTeacher(@PathVariable int id, @RequestBody TeacherInputDto teacher) {
        try {
            return ResponseEntity.ok().body(teacherService.updateTeacher(id, teacher));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int id) {
        try {
            teacherService.deleteTeacherById(id);
            return ResponseEntity.ok().body("Profesor con id: " + id + " borrado");
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
