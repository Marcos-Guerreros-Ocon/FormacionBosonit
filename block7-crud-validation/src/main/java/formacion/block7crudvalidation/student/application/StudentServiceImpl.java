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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.apache.coyote.http11.Constants.a;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto student) throws UnprocessableEntityException, EntityNotFoundException {
        if (student.getId_persona() == null)
            throw new UnprocessableEntityException("El campo id persona es requerido", 442, LocalDateTime.now());

        if (student.getNum_hours_week() == null)
            throw new UnprocessableEntityException("El campo num hours week es requerido", 442, LocalDateTime.now());

        if (student.getBranch() == null)
            throw new UnprocessableEntityException("El campo brank es requerido", 442, LocalDateTime.now());

        Student s = new Student();
        Person p = personRepository.findById(student.getId_persona()).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ninguna persona con el id: " + student.getId_persona(), 404, LocalDateTime.now()));



        s.setPersona(p);
        s.setNum_hours_week(student.getNum_hours_week());
        s.setComents(student.getComents());
        s.setBranch(student.getBranch());

        return studentRepository.save(s).studentToStudentOutputDto();
    }


    @Override
    public StudentOutputDto getStudentById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id, 404, LocalDateTime.now())).studentToStudentOutputDto();

    }

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
}
