package formacion.block7crudvalidationcors.student.application;

import formacion.block7crudvalidationcors.asignatura.domain.Asignatura;
import formacion.block7crudvalidationcors.asignatura.repository.AsignaturaRepository;
import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.person.domain.Person;
import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.person.repository.PersonRepository;
import formacion.block7crudvalidationcors.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidationcors.student.controller.dto.StudentInputDto;
import formacion.block7crudvalidationcors.student.controller.dto.StudentOutputDto;
import formacion.block7crudvalidationcors.student.domain.Student;
import formacion.block7crudvalidationcors.student.repository.StudentRepository;
import formacion.block7crudvalidationcors.teacher.domain.Teacher;
import formacion.block7crudvalidationcors.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;


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
        // 5º Comprobamos si la asignatura existe
        comprobarAsignaturas(student);


        Set<Asignatura> asignaturas = new LinkedHashSet<>();
        if (student.getId_asignaturas() != null)
            student.getId_asignaturas().forEach(id -> asignaturas.add(asignaturaRepository.findById(id).get()));

        // 6º Lo almacenamos en un objeto tipo Teacher
        Student s = new Student();
        Person person = personRepository.findById(student.getId_persona()).get();
        s.setPersona(person);
        s.setNum_hours_week(student.getNum_hours_week());
        s.setComents(student.getComents());
        s.setBranch(student.getBranch());
        s.setAsignaturas(asignaturas);

        return studentRepository.save(s).simpleStudentToStudentOutputDto();
    }


    @Override
    public StudentOutputDto getStudentById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id)).studentToStudentOutputDto();
    }

    @Override
    public SimpleStudentOutputDto getSimpleStudentById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id)).simpleStudentToStudentOutputDto();
    }

    @Override
    public void deleteStudentById(int id) throws EntityNotFoundException {

        studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<SimpleStudentOutputDto> getAllStudent() {
        return studentRepository.findAll().stream().map(Student::simpleStudentToStudentOutputDto).toList();
    }

    @Override
    public StudentOutputDto updateStudent(int id, StudentInputDto student) throws EntityNotFoundException, UnprocessableEntityException {
        Student s = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        if (student.getBranch() != null) s.setBranch(student.getBranch());
        if (student.getComents() != null) s.setComents(student.getComents());
        if (student.getNum_hours_week() != null) s.setNum_hours_week(student.getNum_hours_week());
        if (student.getId_asignaturas() != null) {
            comprobarAsignaturas(student);
            Set<Asignatura> newAsignaturas = new LinkedHashSet<>();
            for (Integer idAs : student.getId_asignaturas()) {
                Asignatura a = asignaturaRepository.findById(idAs).get();
                a.getEstudiantes().add(s);
                newAsignaturas.add(a);
            }

            s.setAsignaturas(newAsignaturas);
        }


        return studentRepository.save(s).studentToStudentOutputDto();
    }

    @Override
    public SimpleStudentOutputDto addAsignaturas(int id, List<Integer> idAsignaturas) throws EntityNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        for (Integer idAsignatura : idAsignaturas) {
            Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con el id: " + id));

            asignatura.getEstudiantes().add(student);
            student.getAsignaturas().add(asignatura);
        }

        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public SimpleStudentOutputDto removeAsignaturas(int id, List<Integer> idAsignaturas) throws EntityNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        for (Integer idAsignatura : idAsignaturas) {
            Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con el id: " + id));

            asignatura.getEstudiantes().remove(student);
            student.getAsignaturas().remove(asignatura);
        }

        return studentRepository.save(student).studentToStudentOutputDto();
    }


    private void comprobarAlumnoExistente(StudentInputDto student) throws UnprocessableEntityException {
        Person person = personRepository.findById(student.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + student.getId_persona());
    }

    private void comprobarProfesorExistente(StudentInputDto student) throws UnprocessableEntityException {
        Person person = personRepository.findById(student.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + student.getId_persona());
    }

    private void comprobarPesona(StudentInputDto student) throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(student.getId_persona());
        if (person.isEmpty())
            throw new EntityNotFoundException("No existe la persona con el id: " + student.getId_persona());
    }

    private void comprobarAlumno(StudentInputDto student) throws UnprocessableEntityException {
        if (student.getId_persona() == null)
            throw new UnprocessableEntityException("El campo id persona es requerido");

        if (student.getNum_hours_week() == null)
            throw new UnprocessableEntityException("El campo num hours week es requerido");

        if (student.getBranch() == null)
            throw new UnprocessableEntityException("El campo brank es requerido");

    }

    private void comprobarAsignaturas(StudentInputDto student) throws EntityNotFoundException {
        if (student.getId_asignaturas() == null) return;

        for (Integer id : student.getId_asignaturas()) {
            Optional<Asignatura> optionalAsignatura = asignaturaRepository.findById(id);
            if (optionalAsignatura.isEmpty())
                throw new EntityNotFoundException("No existe una asigantura con el id: " + id);
        }
    }

}
