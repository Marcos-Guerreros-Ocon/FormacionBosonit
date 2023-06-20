package formacion.springbootreactive.person.application;


import formacion.springbootreactive.exception.UnprocessableEntityException;
import formacion.springbootreactive.person.controller.dto.PersonInputDto;
import formacion.springbootreactive.person.controller.dto.PersonOutputDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<PersonOutputDto> addPerson(PersonInputDto personInputDto) throws UnprocessableEntityException;
    Mono<PersonOutputDto> getPersonById(String id);
    Flux<PersonOutputDto> getAllPeople();
    Mono<PersonOutputDto> updatePersonById(String id, PersonInputDto inputDto);
    Mono<Void> deletePersonById(String id);
}
