package formacion.block7crudvalidation.person.application;

import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.person.controller.dto.FullPersonOutputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.exception.EntityNotFoundException;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException;
    FullPersonOutputDto getFullPersonById(int id) throws EntityNotFoundException;
    PersonOutputDto getPersonById(int id) throws EntityNotFoundException;

    void deletePersonById(int id) throws EntityNotFoundException;

    Iterable<PersonOutputDto> getAllPerson();

    Iterable<PersonOutputDto> getPeopleByName(String name);

    PersonOutputDto updatePerson(int id, PersonInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
