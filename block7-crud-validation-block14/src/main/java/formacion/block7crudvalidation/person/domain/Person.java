package formacion.block7crudvalidation.person.domain;

import formacion.block7crudvalidation.person.controller.dto.FullPersonOutputDto;
import formacion.block7crudvalidation.person.controller.dto.*;
import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.teacher.domain.Teacher;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    Integer id;

    @NotNull(message = "El campo usuario es requerido")
    @Size(min = 6, max = 10, message = "El tamaño del campo usuario debe estar entre 6 a 10 caracteres")
    String usuario;

    @NotNull(message = "El campo contraseña es requerido")
    String password;

    @NotNull(message = "El campo name es requerido")
    String name;

    @NotNull(message = "El campo surname es requerido")
    String surname;

    @NotNull(message = "El campo companyEmail es requerido")
    String companyEmail;

    @NotNull(message = "El campo personalEmail es requerido")
    String personalEmail;

    @NotNull(message = "El campo city es requerido")
    String city;

    @NotNull(message = "El campo active es requerido")
    boolean active;

    @NotNull(message = "El campo createdDate es requerido")
    Date createdDate;

    String imagenUrl;

    Date terminationDate;

    Boolean admin;

    @OneToOne
    @JoinColumn(name = "person")
    Teacher teacher;

    @OneToOne
    @JoinColumn(name = "persona")
    Student student;

    public Person(PersonInputDto personInputDto) {
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
        this.admin = personInputDto.isAdmin();
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

    public FullPersonOutputDto personToFullOutputDto() {
        FullPersonOutputDto personTeacherOutputDto = new FullPersonOutputDto();

        personTeacherOutputDto.setId(this.id);
        personTeacherOutputDto.setUsuario(this.usuario);
        personTeacherOutputDto.setName(this.name);
        personTeacherOutputDto.setSurname(this.surname);
        personTeacherOutputDto.setCompanyEmail(this.companyEmail);
        personTeacherOutputDto.setPersonalEmail(this.personalEmail);
        personTeacherOutputDto.setCity(this.city);
        personTeacherOutputDto.setActive(this.active);
        personTeacherOutputDto.setCreatedDate(this.createdDate);
        personTeacherOutputDto.setImagenUrl(this.imagenUrl);
        personTeacherOutputDto.setTerminationDate(this.terminationDate);

        return personTeacherOutputDto;
    }

}
