package formacion.springbootreactive.person.application;


import formacion.springbootreactive.exception.EntityNotFoundException;
import formacion.springbootreactive.exception.UnprocessableEntityException;
import formacion.springbootreactive.person.controller.dto.PersonInputDto;
import formacion.springbootreactive.person.controller.dto.PersonOutputDto;
import formacion.springbootreactive.person.domain.Person;
import formacion.springbootreactive.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public Mono<PersonOutputDto> addPerson(PersonInputDto personInputDto) throws UnprocessableEntityException {

        if (personInputDto.getName() == null || personInputDto.getName().trim().length() == 0)
            throw new UnprocessableEntityException("El campo name es requerido");
        if (personInputDto.getCity() == null || personInputDto.getCity().trim().length() == 0)
            throw new UnprocessableEntityException("El campo city es requerido");
        if (personInputDto.getAge() == null)
            throw new UnprocessableEntityException("El campo age es requerido");

        return personRepository.save(new Person(personInputDto)).map(Person::personToPersonOutput);
    }

    @Override
    public Mono<PersonOutputDto> getPersonById(String id) {

        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("No se encuentra la persona con id: " + id)))
                .map(Person::personToPersonOutput);
    }

    @Override
    public Flux<PersonOutputDto> getAllPeople() {
        return personRepository.findAll().map(Person::personToPersonOutput);
    }

    @Override
    public Mono<PersonOutputDto> updatePersonById(String id, PersonInputDto inputDto) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("No se encuentra la persona con id: " + id)))
                .map(p -> {
                    if (inputDto.getName() != null)
                        p.setName(inputDto.getName());
                    if (inputDto.getCity() != null)
                        p.setCity(inputDto.getCity());
                    if (inputDto.getAge() != null)
                        p.setAge(inputDto.getAge());
                    return p;
                }).
                flatMap(personRepository::save).map(Person::personToPersonOutput);
    }

    @Override
    public Mono<Void> deletePersonById(String id) {
        return personRepository.deleteById(id);
    }


}
