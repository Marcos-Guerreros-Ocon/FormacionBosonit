package formacion.block7crud.application;

import formacion.block7crud.controller.dto.PersonInputDto;
import formacion.block7crud.controller.dto.PersonOutputDto;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);

    PersonOutputDto getPersonById(int id);

    void deletePersonById(int id);

    Iterable<PersonOutputDto> getAllPerson();

    Iterable<PersonOutputDto> getPeopleByName(String name);

    PersonOutputDto updatePerson(int id, PersonInputDto person);
}
