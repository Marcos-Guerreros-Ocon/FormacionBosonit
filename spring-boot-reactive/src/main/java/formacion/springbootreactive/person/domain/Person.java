package formacion.springbootreactive.person.domain;


import formacion.springbootreactive.person.controller.dto.PersonInputDto;
import formacion.springbootreactive.person.controller.dto.PersonOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Person")
public class Person implements Serializable {


    @Id
    private String id;
    private String name;
    private String city;
    private Integer age;

    public Person(PersonInputDto personInputDto) {
        setName(personInputDto.getName());
        setCity(personInputDto.getCity());
        setAge(personInputDto.getAge());
    }

    public PersonOutputDto personToPersonOutput() {
        PersonOutputDto personOutputDto = new PersonOutputDto();
        personOutputDto.setId(getId());
        personOutputDto.setName(getName());
        personOutputDto.setCity(getCity());
        personOutputDto.setAge(getAge());

        return personOutputDto;
    }
}
