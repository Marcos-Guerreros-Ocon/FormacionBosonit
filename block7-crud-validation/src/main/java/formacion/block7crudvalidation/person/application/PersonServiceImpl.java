package formacion.block7crudvalidation.person.application;

import formacion.block7crudvalidation.person.controller.dto.FullPersonOutputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.student.repository.StudentRepository;
import formacion.block7crudvalidation.teacher.domain.Teacher;
import formacion.block7crudvalidation.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {

        if (person.getUsuario() == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "El campo usuario no puede ser nulo.");

        if (person.getUsuario().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo usuario debe tener como minimo 6 caracteres.");

        if (person.getUsuario().length() > 10)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo usuario debe tener como maximo 10 caracteres.");

        if (person.getPassword() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo contraseña no puede ser nulo.");

        if (person.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo name no puede ser nulo.");

        if (person.getCompanyEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo company email no puede ser nulo.");

        if (person.getPersonalEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo personal email no puede ser nulo.");

        if (person.getCreatedDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo created email no puede ser nulo.");

        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id) throws EntityNotFoundException {

        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encotrada con el id " + id, 404, LocalDateTime.now())).personToPersonOutputDto();
    }

    public FullPersonOutputDto getFullPersonById(int id) throws EntityNotFoundException {
        Person p = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encotrada con el id " + id, 404, LocalDateTime.now()));

        FullPersonOutputDto aux = p.personToFullOutputDto();
        Optional<Teacher> optionalTeacher = teacherRepository.findByPerson(p);
        Optional<Student> optionalStudent = studentRepository.findByPersona(p);
        Teacher teacher = null;
        Student student = null;

        if (!optionalTeacher.isEmpty()) {
            teacher = optionalTeacher.get();
            teacher.setPerson(null);
        }

        if (!optionalStudent.isEmpty()) {
            student = optionalStudent.get();
            student.setPersona(null);
        }

        aux.setTeacher(teacher);
        aux.setStudent(student);

        return aux;


    }

    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow();

        personRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonOutputDto> getAllPerson() {
        return personRepository.findAll().stream().map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public Iterable<PersonOutputDto> getPeopleByName(String name) {
        return personRepository.findByName(name).stream().map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto person) throws EntityNotFoundException {
        Person p = personRepository.findById(id).get();

        if (p == null) {
            throw new EntityNotFoundException("Persona no encontrada", 404, LocalDateTime.now());
        }
        if (person.getUsuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo usuario no puede ser nulo.");

        if (person.getUsuario().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo usuario debe tener como minimo 6 caracteres.");

        if (person.getUsuario().length() > 10)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo usuario debe tener como maximo 10 caracteres.");

        if (person.getPassword() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo contraseña no puede ser nulo.");

        if (person.getCompanyEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo company email no puede ser nulo.");

        if (person.getPersonalEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo personal email no puede ser nulo.");

        if (person.getCreatedDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo created email no puede ser nulo.");


        p.setUsuario(person.getUsuario());
        p.setPassword(person.getPassword());
        p.setName(person.getName());
        p.setSurname(person.getSurname());
        p.setCompanyEmail(person.getCompanyEmail());
        p.setPersonalEmail(person.getPersonalEmail());
        p.setCity(person.getCity());
        p.setActive(person.isActive());
        p.setCreatedDate(person.getCreatedDate());
        p.setImagenUrl(person.getImagenUrl());
        p.setTerminationDate(person.getTerminationDate());

        return personRepository.save(p).personToPersonOutputDto();
    }
}
