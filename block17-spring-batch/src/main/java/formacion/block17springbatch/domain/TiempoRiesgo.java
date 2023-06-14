package formacion.block17springbatch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class TiempoRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTiempoRiesgo;
    private String localidad;
    private int mes;
    private int ano;
    private Integer temperatura;
    private String riesgo;

    @OneToOne
    private Tiempo tiempo;


}