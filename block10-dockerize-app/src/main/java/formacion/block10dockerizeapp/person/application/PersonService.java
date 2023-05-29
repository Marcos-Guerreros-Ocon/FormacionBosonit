package formacion.block10dockerizeapp.person.application;

import formacion.block10dockerizeapp.exception.UnprocessableEntityException;
import formacion.block10dockerizeapp.person.controller.dto.FullPersonOutputDto;
import formacion.block10dockerizeapp.person.controller.dto.PersonInputDto;
import formacion.block10dockerizeapp.person.controller.dto.PersonOutputDto;
import formacion.block10dockerizeapp.exception.EntityNotFoundException;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException;
    FullPersonOutputDto getFullPersonById(int id) throws EntityNotFoundException;
    PersonOutputDto getPersonById(int id) throws EntityNotFoundException;

    void deletePersonById(int id) throws EntityNotFoundException;

    Iterable<PersonOutputDto> getAllPerson();

    Iterable<PersonOutputDto> getPeopleByName(String name);

    PersonOutputDto updatePerson(int id, PersonInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
