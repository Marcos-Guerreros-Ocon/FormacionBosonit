package formacion.block17springbatch.steps.step4;

import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import formacion.block17springbatch.tiemporiesgo.mapper.TiempoRiesgoRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;

import javax.sql.DataSource;

public class TiempoRiesgoDatabaseReader extends JdbcCursorItemReader<TiempoRiesgo> {
    public TiempoRiesgoDatabaseReader(DataSource dataSource){
        setSql("SELECT t.ciudad,t.numero_ano,t.numero_mes,t.numero_temperaturas,t.temperatura_media,t.riesgo"
                + " FROM tiempo_riesgo AS t");
        setDataSource(dataSource);
        setFetchSize(100);
        setRowMapper(new TiempoRiesgoRowMapper());
    }
}
