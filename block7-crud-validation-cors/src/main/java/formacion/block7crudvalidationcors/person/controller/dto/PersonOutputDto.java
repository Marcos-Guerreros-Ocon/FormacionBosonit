package formacion.block7crudvalidationcors.person.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {

    private int id;

    private String usuario;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;
}
