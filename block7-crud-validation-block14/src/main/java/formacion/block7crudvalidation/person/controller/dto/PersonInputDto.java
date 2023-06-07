package formacion.block7crudvalidation.person.controller.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String companyEmail;

    private String personalEmail;

    private String city;

    private boolean active;

    private Date createdDate;

    private String imagenUrl;

    private Date terminationDate;

    private boolean admin;
}
