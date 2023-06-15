package formacion.block17springbatch.tiemporiesgo.mapper;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRiesgoRowMapper implements RowMapper<TiempoRiesgo> {
    @Override
    public TiempoRiesgo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setCiudad(rs.getString("ciudad"));
        tiempoRiesgo.setNumeroAno(rs.getInt("numero_ano"));
        tiempoRiesgo.setNumeroMes(rs.getInt("numero_mes"));
        tiempoRiesgo.setNumeroTemperaturas(rs.getInt("numero_temperaturas"));
        tiempoRiesgo.setTemperaturaMedia(rs.getFloat("temperatura_media"));
        tiempoRiesgo.setRiesgo(rs.getString("riesgo"));
        return tiempoRiesgo;
    }
}
