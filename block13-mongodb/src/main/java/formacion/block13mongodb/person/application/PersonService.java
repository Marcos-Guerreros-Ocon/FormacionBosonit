package formacion.block13mongodb.person.application;




import formacion.block13mongodb.exception.EntityNotFoundException;
import formacion.block13mongodb.exception.UnprocessableEntityException;
import formacion.block13mongodb.person.controller.dto.PersonInputDto;
import formacion.block13mongodb.person.controller.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto personaInputDto) throws UnprocessableEntityException;
    List<PersonOutputDto> getPeople(int pagina);
    List<PersonOutputDto> getAllPeople();
    PersonOutputDto getPersonById(String id) throws EntityNotFoundException;
    PersonOutputDto updatePerson(String id, PersonInputDto personaInputDto) throws EntityNotFoundException;
    void deletePersonById(String id) throws EntityNotFoundException;
}
