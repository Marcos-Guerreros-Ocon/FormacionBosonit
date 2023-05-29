package formacion.block10dockerizeapp.teacher.application;

import formacion.block10dockerizeapp.exception.EntityNotFoundException;
import formacion.block10dockerizeapp.exception.UnprocessableEntityException;
import formacion.block10dockerizeapp.person.domain.Person;
import formacion.block10dockerizeapp.person.repository.PersonRepository;
import formacion.block10dockerizeapp.student.domain.Student;
import formacion.block10dockerizeapp.student.repository.StudentRepository;
import formacion.block10dockerizeapp.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block10dockerizeapp.teacher.controller.dto.TeacherInputDto;
import formacion.block10dockerizeapp.teacher.domain.Teacher;
import formacion.block10dockerizeapp.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id)).teacherToSimpleTeacherOutputDto();
    }

    @Override
    public void deleteTeacherById(int id) throws EntityNotFoundException {
        teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id));
        teacherRepository.deleteById(id);
    }

    @Override
    public Iterable<SimpleTeacherOutputDto> getAllTeacher() {
        return teacherRepository.findAll().stream().map(Teacher::teacherToSimpleTeacherOutputDto).toList();
    }

    @Override
    public SimpleTeacherOutputDto updateTeacher(int id, TeacherInputDto newTeacher) throws EntityNotFoundException, UnprocessableEntityException {
        Teacher oldTeacher = teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un profesor con el id: " + id));
        comprobarTeacher(newTeacher);
        comprobarPesona(newTeacher);
        comprobarAlumnoExistente(newTeacher);

        if (newTeacher.getComents() != null) oldTeacher.setComents(newTeacher.getComents());
        oldTeacher.setBranch(newTeacher.getBranch());


        return teacherRepository.save(oldTeacher).teacherToSimpleTeacherOutputDto();
    }

    private void comprobarTeacher(TeacherInputDto teacherInputDto) throws UnprocessableEntityException {
        if (teacherInputDto.getId_persona() == null)
            throw new UnprocessableEntityException("El campo id persona es requerido");
        if (teacherInputDto.getBranch() == null)
            throw new UnprocessableEntityException("El campo branch es requerido");
    }

    private void comprobarPesona(TeacherInputDto teacher) throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(teacher.getId_persona());
        if (person.isEmpty())
            throw new EntityNotFoundException("No existe la persona con el id: " + teacher.getId_persona());
    }

    private void comprobarAlumnoExistente(TeacherInputDto teacher) throws UnprocessableEntityException {
        Person person = personRepository.findById(teacher.getId_persona()).get();
        Optional<Student> aux = studentRepository.findByPersona(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un alumno con el id de persona: " + teacher.getId_persona());
    }

    private void comprobarProfesorExistente(TeacherInputDto teacher) throws UnprocessableEntityException {
        Person person = personRepository.findById(teacher.getId_persona()).get();
        Optional<Teacher> aux = teacherRepository.findByPerson(person);
        if (!aux.isEmpty())
            throw new UnprocessableEntityException("Ya existe un profesor con el id de persona: " + teacher.getId_persona());
    }


}
