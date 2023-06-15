package formacion.block17springbatch.tiempo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Tiempo {
    @Id
    @GeneratedValue
    private Integer id;
    private String ciudad;
    private Date fecha;
    private Integer temperatura;

}
