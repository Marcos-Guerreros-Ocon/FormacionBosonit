package formacion.block7crud.domain;

import formacion.block7crud.controller.dto.PersonInputDto;
import formacion.block7crud.controller.dto.PersonOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    int id;
    String name;
    int age;
    String town;

    public Person(PersonInputDto personInputDto) {
        this.id = personInputDto.getId();
        this.name = personInputDto.getName();
        this.age = personInputDto.getAge();
        this.town = personInputDto.getTown();
    }

    public PersonOutputDto personToPersonOutputDto() {
        return new PersonOutputDto(this.id, this.name, this.age, this.town);
    }
}
