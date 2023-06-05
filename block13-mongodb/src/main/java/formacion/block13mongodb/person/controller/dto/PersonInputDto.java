package formacion.block13mongodb.person.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {
    private String id;
    private String name;
    private String city;
    private Integer age;

}
