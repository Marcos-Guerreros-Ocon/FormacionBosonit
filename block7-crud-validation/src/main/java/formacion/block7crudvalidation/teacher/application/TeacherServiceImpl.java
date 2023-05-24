package formacion.block7crudvalidation.teacher.application;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.student.repository.StudentRepository;
import formacion.block7crudvalidation.teacher.controller.TeacherController;
import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import formacion.block7crudvalidation.teacher.domain.Teacher;
import formacion.block7crudvalidation.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;


    @Override
    public SimpleTeacherOutputDto addTeacher(TeacherInputDto teacher) throws UnprocessableEntityException, EntityNotFoundException {

        // 1º Comprobamos los campos que le tenemos que pasar que no sean nulos
        comprobarTeacher(teacher);
        // 2º Comprobamos si la persona existe
        comprobarPesona(teacher);
        // 3º Comprobamos si la persona es ya profesor
        comprobarProfesorExistente(teacher);
        // 4º Comprobamos si la persona ya es un alumno
        comprobarAlumnoExistente(teacher);

        // 5º Lo almacenamos en un objeto tipo Teacher
        Teacher t = new Teacher();
        Person person = personRepository.findById(teacher.getId_persona()).get();
        t.setPerson(person);
        t.setBranch(teacher.getBranch());
        t.setComents(teacher.getComents());

        return teacherRepository.save(t).teacherToSimpleTeacherOutputDto();

    }


    @Override
    public SimpleTeacherOutputDto getTeacherById(int id) throws EntityNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id, HttpStatus.NOT_FOUND.value(), LocalDateTime.now())).teacherToSimpleTeacherOutputDto();
    }

    @Override
    public void deleteTeacherById(int id) throws EntityNotFoundException {
        teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id, HttpStatus.NOT_FOUND.value(), LocalDateTime.now()));
        teacherRepository.deleteById(id);
    }

    @Override
    public Iterable<SimpleTeacherOutputDto> getAllTeacher() {
        return teacherRepository.findAll().stream().map(Teacher::teacherToSimpleTeacherOutputDto).toList();
    }

    @Override
    public SimpleTeacherOutputDto updateTeacher(int id, TeacherInputDto newTeacher) throws EntityNotFoundException, UnprocessableEntityException {
        Teacher oldTeacher = teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id, HttpStatus.NOT_FOUND.value(), LocalDateTime.now()));
        comprobarTeacher(newTeacher);
        comprobarPesona(newTeacher);
        comprobarAlumnoExistente(newTeacher);

        if (newTeacher.getComents() != null) oldTeacher.setComents(newTeacher.getComents());
        oldTeacher.setBranch(newTeacher.getBranch());


        return teacherRepository.save(oldTeacher).teacherToSimpleTeacherOutputDto();
    }

    private void comprobarTeacher(TeacherInputDto teacherInputDto) throws UnprocessableEntityException {
        if (teacherInputDto.getId_persona() == null)
            throw new UnprocessableEntityException("El campo id persona es requerido", HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
        if (teacherInputDto.getBranch() == null)
            throw new UnprocessableEntityException("El campo branch es requerido", HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
    }

    private void comprobarPesona(TeacherInputDto teacher) throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(teacher.getId_persona());
        if (person.isEmpty())
            throw new EntityNotFoundException("No existe la persona con el id: " + teacher.getId_persona(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    private void comprobarAlumnoExistente(TeacherInputDto teacher) throws UnprocessableEntityException {
        Person person = personRepository.findById(teacher.getId_persona()).get();
        Optional<Student> aux = studentRepository.findByPersona(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un alumno con el id de persona: " + teacher.getId_persona(), HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
    }

    private void comprobarProfesorExistente(TeacherInputDto teacher) throws UnprocessableEntityException {
        Person person = personRepository.findById(teacher.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + teacher.getId_persona(), HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
    }


}
