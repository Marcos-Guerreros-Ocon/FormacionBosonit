package formacion.block7crudvalidation.domain;

import formacion.block7crudvalidation.controller.dto.PersonInputDto;
import formacion.block7crudvalidation.controller.dto.PersonOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 6, max = 10)
    private String usuario;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String companyEmail;

    @NotNull
    private String personalEmail;

    @NotNull
    private String city;

    @NotNull
    private boolean active;

    @NotNull
    private Date createdDate;

    private String imagenUrl;

    private Date terminationDate;

    public Person(PersonInputDto personInputDto) {
        this.id = personInputDto.getId();
        this.usuario = personInputDto.getUsuario();
        this.password = personInputDto.getPassword();
        this.name = personInputDto.getName();
        this.surname = personInputDto.getSurname();
        this.companyEmail = personInputDto.getCompanyEmail();
        this.personalEmail = personInputDto.getPersonalEmail();
        this.city = personInputDto.getCity();
        this.active = personInputDto.isActive();
        this.createdDate = personInputDto.getCreatedDate();
        this.imagenUrl = personInputDto.getImagenUrl();
        this.terminationDate = personInputDto.getTerminationDate();
    }

    public PersonOutputDto personToPersonOutputDto() {
        PersonOutputDto personOutputDto = new PersonOutputDto();
        personOutputDto.setId(this.id);
        personOutputDto.setUsuario(this.usuario);
        personOutputDto.setName(this.name);
        personOutputDto.setSurname(this.surname);
        personOutputDto.setCompanyEmail(this.companyEmail);
        personOutputDto.setPersonalEmail(this.personalEmail);
        personOutputDto.setCity(this.city);
        personOutputDto.setActive(this.active);
        personOutputDto.setCreatedDate(this.createdDate);
        personOutputDto.setImagenUrl(this.imagenUrl);
        personOutputDto.setTerminationDate(this.terminationDate);

        return personOutputDto;
    }

}
