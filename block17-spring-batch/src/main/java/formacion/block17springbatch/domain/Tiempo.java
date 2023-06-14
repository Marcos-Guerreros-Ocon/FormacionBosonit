package formacion.block17springbatch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Tiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTiempo;
    private String localidad;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Integer temperatura;
}