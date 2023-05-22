package formacion.block7crudvalidation.application;

import formacion.block7crudvalidation.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.domain.Person;
import formacion.block7crudvalidation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
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

        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
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
    public PersonOutputDto updatePerson(int id, PersonInputDto person) {
        Person p = personRepository.findById(id).orElseThrow();

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
