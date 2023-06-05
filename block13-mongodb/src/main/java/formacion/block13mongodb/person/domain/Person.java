package formacion.block13mongodb.person.domain;



import formacion.block13mongodb.person.controller.dto.PersonInputDto;
import formacion.block13mongodb.person.controller.dto.PersonOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "person")
public class Person {
    @Id
    private String id;
    private String name;
    private String city;
    private Integer age;

    public Person(PersonInputDto personaInputDto) {
        setId(personaInputDto.getId());
        setName(personaInputDto.getName());
        setCity(personaInputDto.getCity());
        setAge(personaInputDto.getAge());
    }

    public PersonOutputDto personToPersonOutputDto() {
        PersonOutputDto personOutputDto = new PersonOutputDto();
        personOutputDto.setId(getId());
        personOutputDto.setName(getName());
        personOutputDto.setCity(getCity());
        personOutputDto.setAge(getAge());

        return personOutputDto;
    }
}
