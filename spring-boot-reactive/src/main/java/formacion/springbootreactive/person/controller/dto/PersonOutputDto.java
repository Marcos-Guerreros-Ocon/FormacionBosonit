package formacion.springbootreactive.person.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {
    private String id;
    private String name;
    private String city;
    private Integer age;
}
