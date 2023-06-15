package formacion.block17springbatch.tiempo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiempoOutputDTO {
    private String ciudad;
    private String fecha;
    private Integer temperatura;

    public TiempoOutputDTO(Tiempo t) {
        this.ciudad = t.getCiudad();
        this.fecha = new SimpleDateFormat("dd/MM/yyyy").format(t.getFecha());
        this.temperatura = t.getTemperatura();
    }
}
