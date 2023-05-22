package formacion.block7crudvalidation.application;

import formacion.block7crudvalidation.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.controller.dto.PersonOutputDto;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);

    PersonOutputDto getPersonById(int id);

    void deletePersonById(int id);

    Iterable<PersonOutputDto> getAllPerson();

    Iterable<PersonOutputDto> getPeopleByName(String name);

    PersonOutputDto updatePerson(int id, PersonInputDto person);
}
