package formacion.block13mongodb.person.application;


import formacion.block13mongodb.exception.EntityNotFoundException;
import formacion.block13mongodb.exception.UnprocessableEntityException;
import formacion.block13mongodb.person.controller.dto.PersonInputDto;
import formacion.block13mongodb.person.controller.dto.PersonOutputDto;
import formacion.block13mongodb.person.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final int PAGE_SIZE =2;

    private final MongoTemplate mongoTemplate;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personaInputDto) throws UnprocessableEntityException {
        if (personaInputDto.getName() == null) throw new UnprocessableEntityException("El campo name es requerido");
        if (personaInputDto.getAge() == null) throw new UnprocessableEntityException("El campo city es requerido");
        if (personaInputDto.getCity() == null) throw new UnprocessableEntityException("El campo age es requerido");

        Person persona = new Person(personaInputDto);
        return mongoTemplate.save(persona).personToPersonOutputDto();
    }

    @Override
    public List<PersonOutputDto> getPeople(int pagina) {
        if (pagina <= 0) return null;


        List<Person> persons = mongoTemplate.findAll(Person.class);
        int perPage = (pagina - 1) * PAGE_SIZE;
        return persons.stream().skip(perPage)
                .limit(PAGE_SIZE).map(Person::personToPersonOutputDto)
                .toList();
    }

    @Override
    public List<PersonOutputDto> getAllPeople() {
        List<Person> persons = mongoTemplate.findAll(Person.class);
        return persons.stream().map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto getPersonById(String id) throws EntityNotFoundException {
        Person p = mongoTemplate.findById(id, Person.class);
        if (p == null) throw new EntityNotFoundException("No se encuentra una persona con id: " + id);

        return p.personToPersonOutputDto();

    }

    @Override
    public PersonOutputDto updatePerson(String id, PersonInputDto personaInputDto) throws EntityNotFoundException {
        Person oldPerson = mongoTemplate.findById(id, Person.class);
        if (oldPerson == null) throw new EntityNotFoundException("No se encuentra una persona con id: " + id);

        if (personaInputDto.getName() != null) oldPerson.setName(personaInputDto.getName());
        if (personaInputDto.getCity() != null) oldPerson.setName(personaInputDto.getCity());
        if (personaInputDto.getAge() != null) oldPerson.setAge(personaInputDto.getAge());

        return mongoTemplate.save(oldPerson).personToPersonOutputDto();

    }

    @Override
    public void deletePersonById(String id) throws EntityNotFoundException {
        Person person = mongoTemplate.findById(id, Person.class);

        if (person == null) throw new EntityNotFoundException("No se encuentra una persona con id: " + id);

        mongoTemplate.remove(person);
    }
}


