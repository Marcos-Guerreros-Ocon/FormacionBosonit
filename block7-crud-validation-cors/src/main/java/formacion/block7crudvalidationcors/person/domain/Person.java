package formacion.block7crudvalidationcors.person.domain;

import formacion.block7crudvalidationcors.person.controller.dto.FullPersonOutputDto;
import formacion.block7crudvalidationcors.person.controller.dto.*;
import formacion.block7crudvalidationcors.student.domain.Student;
import formacion.block7crudvalidationcors.teacher.domain.Teacher;
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
        this.companyEmail = personInputDto.getCompany_email();
        this.personalEmail = personInputDto.getPersonal_email();
        this.city = personInputDto.getCity();
        this.active = personInputDto.isActive();
        this.createdDate = personInputDto.getCreated_date();
        this.imagenUrl = personInputDto.getImagen_url();
        this.terminationDate = personInputDto.getTermination_date();
    }

    public PersonOutputDto personToPersonOutputDto() {
        PersonOutputDto personOutputDto = new PersonOutputDto();
        personOutputDto.setId(this.id);
        personOutputDto.setUsuario(this.usuario);
        personOutputDto.setName(this.name);
        personOutputDto.setSurname(this.surname);
        personOutputDto.setCompany_email(this.companyEmail);
        personOutputDto.setPersonal_email(this.personalEmail);
        personOutputDto.setCity(this.city);
        personOutputDto.setActive(this.active);
        personOutputDto.setCreated_date(this.createdDate);
        personOutputDto.setImagen_url(this.imagenUrl);
        personOutputDto.setTermination_date(this.terminationDate);

        return personOutputDto;
    }

    public FullPersonOutputDto personToFullOutputDto() {
        FullPersonOutputDto personTeacherOutputDto = new FullPersonOutputDto();

        personTeacherOutputDto.setId(this.id);
        personTeacherOutputDto.setUsuario(this.usuario);
        personTeacherOutputDto.setName(this.name);
        personTeacherOutputDto.setSurname(this.surname);
        personTeacherOutputDto.setCompany_email(this.companyEmail);
        personTeacherOutputDto.setPersonal_email(this.personalEmail);
        personTeacherOutputDto.setCity(this.city);
        personTeacherOutputDto.setActive(this.active);
        personTeacherOutputDto.setCreated_date(this.createdDate);
        personTeacherOutputDto.setImagen_url(this.imagenUrl);
        personTeacherOutputDto.setTermination_date(this.terminationDate);

        return personTeacherOutputDto;
    }

}
