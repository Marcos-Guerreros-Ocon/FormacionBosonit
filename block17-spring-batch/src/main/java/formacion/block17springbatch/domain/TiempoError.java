package formacion.block17springbatch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class TiempoError {
    private Integer idTiempo;
    private String localidad;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Integer temperatura;
}
