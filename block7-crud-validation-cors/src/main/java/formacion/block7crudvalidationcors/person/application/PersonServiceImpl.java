package formacion.block7crudvalidationcors.person.application;

import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.person.controller.dto.FullPersonOutputDto;
import formacion.block7crudvalidationcors.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidationcors.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidationcors.person.domain.Person;
import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.person.repository.PersonRepository;
import formacion.block7crudvalidationcors.student.domain.Student;
import formacion.block7crudvalidationcors.student.repository.StudentRepository;
import formacion.block7crudvalidationcors.teacher.domain.Teacher;
import formacion.block7crudvalidationcors.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException {

        if (person.getUsuario() == null)
            throw new UnprocessableEntityException("El campo usuario no puede ser nulo.");

        if (person.getUsuario().length() < 6)
            throw new UnprocessableEntityException("El campo usuario debe tener como minimo 6 caracteres.");

        if (person.getUsuario().length() > 10)
            throw new UnprocessableEntityException("El campo usuario debe tener como maximo 10 caracteres.");

        if (person.getPassword() == null)
            throw new UnprocessableEntityException("El campo contraseña no puede ser nulo.");

        if (person.getName() == null)
            throw new UnprocessableEntityException("El campo name no puede ser nulo.");

        if (person.getCompany_email() == null)
            throw new UnprocessableEntityException("El campo company email no puede ser nulo.");

        if (person.getPersonal_email() == null)
            throw new UnprocessableEntityException("El campo personal email no puede ser nulo.");

        if (person.getCreated_date() == null)
            throw new UnprocessableEntityException("El campo created date no puede ser nulo.");

        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id) throws EntityNotFoundException {

        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encotrada con el id " + id)).personToPersonOutputDto();
    }

    @Override
    public FullPersonOutputDto getFullPersonById(int id) throws EntityNotFoundException {
        Person p = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encotrada con el id " + id));

        FullPersonOutputDto aux = p.personToFullOutputDto();
        Optional<Teacher> optionalTeacher = teacherRepository.findByPerson(p);
        Optional<Student> optionalStudent = studentRepository.findByPersona(p);
        Teacher teacher = null;
        Student student = null;

        if (!optionalTeacher.isEmpty()) {
            teacher = optionalTeacher.get();
            aux.setTeacher(teacher.teacherToSimpleTeacherOutputDto());

        }

        if (!optionalStudent.isEmpty()) {
            student = optionalStudent.get();
            aux.setStudent(student.studentToStudentOutputDto());

        }
        return aux;
    }

    @Override
    public void deletePersonById(int id) throws EntityNotFoundException {
        personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encotrada con el id " + id));
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
    public PersonOutputDto updatePerson(int id, PersonInputDto person) throws EntityNotFoundException, UnprocessableEntityException {
        Person p = personRepository.findById(id).get();

        if (p == null) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
        if (person.getUsuario() == null)
            throw new UnprocessableEntityException("El campo usuario no puede ser nulo.");

        if (person.getUsuario().length() < 6)
            throw new UnprocessableEntityException("El campo usuario debe tener como minimo 6 caracteres.");

        if (person.getUsuario().length() > 10)
            throw new UnprocessableEntityException("El campo usuario debe tener como maximo 10 caracteres.");

        if (person.getPassword() == null)
            throw new UnprocessableEntityException("El campo contraseña no puede ser nulo.");

        if (person.getCompany_email() == null)
            throw new UnprocessableEntityException("El campo company email no puede ser nulo.");

        if (person.getPersonal_email() == null)
            throw new UnprocessableEntityException("El campo personal email no puede ser nulo.");

        if (person.getCreated_date() == null)
            throw new UnprocessableEntityException("El campo created date no puede ser nulo.");


        p.setUsuario(person.getUsuario());
        p.setPassword(person.getPassword());
        p.setName(person.getName());
        p.setSurname(person.getSurname());
        p.setCompanyEmail(person.getCompany_email());
        p.setPersonalEmail(person.getPersonal_email());
        p.setCity(person.getCity());
        p.setActive(person.isActive());
        p.setCreatedDate(person.getCreated_date());
        p.setImagenUrl(person.getImagen_url());
        p.setTerminationDate(person.getTermination_date());

        return personRepository.save(p).personToPersonOutputDto();
    }
}
