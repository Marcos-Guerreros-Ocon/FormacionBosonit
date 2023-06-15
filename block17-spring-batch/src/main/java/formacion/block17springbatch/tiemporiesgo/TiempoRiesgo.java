package formacion.block17springbatch.tiemporiesgo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class TiempoRiesgo {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String ciudad;
    @Column
    private Integer numeroAno;
    @Column
    private Integer numeroMes;
    @Column
    private Integer numeroTemperaturas;
    @Column
    private Float temperaturaMedia;
    @Column
    private String riesgo;

}
