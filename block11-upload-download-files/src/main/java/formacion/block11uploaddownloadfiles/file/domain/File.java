package formacion.block11uploaddownloadfiles.file.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    private String fecha_subida;

    private String categoria;
}