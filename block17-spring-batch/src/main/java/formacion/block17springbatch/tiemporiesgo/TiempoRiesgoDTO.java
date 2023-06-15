package formacion.block17springbatch.tiemporiesgo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiempoRiesgoDTO {
    private String ciudad;
    private Integer numeroAno;
    private Integer numeroMes;
    private Integer numeroTemperaturas;
    private Float temperaturaMedia;

    public TiempoRiesgoDTO(String ciudad, Integer ano, Integer mes, Long numeroTemperaturas, Double temperaturaMedia) {
        this.ciudad = ciudad;
        this.numeroAno = ano;
        this.numeroMes = mes;
        this.numeroTemperaturas = (int) (long) numeroTemperaturas;
        this.temperaturaMedia = (float)(double) temperaturaMedia;
    }
}
