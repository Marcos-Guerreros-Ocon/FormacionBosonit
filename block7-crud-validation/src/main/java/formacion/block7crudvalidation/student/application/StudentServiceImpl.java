package formacion.block7crudvalidation.student.application;

import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.student.repository.StudentRepository;
import formacion.block7crudvalidation.teacher.domain.Teacher;
import formacion.block7crudvalidation.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.apache.coyote.http11.Constants.a;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public SimpleStudentOutputDto addStudent(StudentInputDto student) throws UnprocessableEntityException, EntityNotFoundException {

        // 1º Comprobamos los campos que le tenemos que pasar que no sean nulos
        comprobarAlumno(student);
        // 2º Comprobamos si la persona existe
        comprobarPesona(student);
        // 3º Comprobamos si la persona es ya profesor
        comprobarProfesorExistente(student);
        // 4º Comprobamos si la persona ya es un alumno
        comprobarAlumnoExistente(student);

        // 5º Lo almacenamos en un objeto tipo Teacher
        Student s = new Student();
        Person person = personRepository.findById(student.getId_persona()).get();
        s.setPersona(person);
        s.setNum_hours_week(student.getNum_hours_week());
        s.setComents(student.getComents());
        s.setBranch(student.getBranch());

        return studentRepository.save(s).simpleStudentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto getStudentById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id, 404, LocalDateTime.now())).studentToStudentOutputDto();

    }
    @Override
    public SimpleStudentOutputDto getSimpleStudentById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id, 404, LocalDateTime.now())).simpleStudentToStudentOutputDto();
    }

    @Override
    public void deleteStudentById(int id) throws EntityNotFoundException {

        getStudentById(id);

        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<SimpleStudentOutputDto> getAllStudent() {
        return studentRepository.findAll().stream().map(Student::simpleStudentToStudentOutputDto).toList();
    }

    @Override
    public StudentOutputDto updateStudent(int id, StudentInputDto student) throws EntityNotFoundException, UnprocessableEntityException {
        getStudentById(id);


        return null;
    }


    private void comprobarAlumnoExistente(StudentInputDto student) throws UnprocessableEntityException {
        Person person = personRepository.findById(student.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + student.getId_persona(), HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
    }

    private void comprobarProfesorExistente(StudentInputDto student) throws UnprocessableEntityException {
        Person person = personRepository.findById(student.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + student.getId_persona(), HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
    }

    private void comprobarPesona(StudentInputDto student) throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(student.getId_persona());
        if (person.isEmpty())
            throw new EntityNotFoundException("No existe la persona con el id: " + student.getId_persona(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    private void comprobarAlumno(StudentInputDto student) throws UnprocessableEntityException {
        if (student.getId_persona() == null)
            throw new UnprocessableEntityException("El campo id persona es requerido", 442, LocalDateTime.now());

        if (student.getNum_hours_week() == null)
            throw new UnprocessableEntityException("El campo num hours week es requerido", 442, LocalDateTime.now());

        if (student.getBranch() == null)
            throw new UnprocessableEntityException("El campo brank es requerido", 442, LocalDateTime.now());

    }

}
