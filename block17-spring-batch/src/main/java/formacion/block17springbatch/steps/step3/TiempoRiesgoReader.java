package formacion.block17springbatch.steps.step3;

import formacion.block17springbatch.tiemporiesgo.TiempoRiesgoDTO;
import formacion.block17springbatch.tiemporiesgo.mapper.TiempoRiesgoDtoRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;

import javax.sql.DataSource;

public class TiempoRiesgoReader extends JdbcCursorItemReader<TiempoRiesgoDTO> {

    public TiempoRiesgoReader(DataSource dataSource){
      setSql("SELECT t.ciudad,YEAR(t.fecha) as y,MONTH(t.fecha) as m,COUNT(t.temperatura) as c,AVG(t.temperatura) as average"
                + " FROM Tiempo AS t GROUP BY t.ciudad,YEAR(t.fecha),MONTH(t.fecha) ORDER BY t.ciudad");
        setDataSource(dataSource);
        setFetchSize(100);
        setRowMapper(new TiempoRiesgoDtoRowMapper());
    }
}
