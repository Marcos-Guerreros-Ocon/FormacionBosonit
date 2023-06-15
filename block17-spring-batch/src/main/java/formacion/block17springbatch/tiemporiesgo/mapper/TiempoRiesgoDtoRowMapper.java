package formacion.block17springbatch.tiemporiesgo.mapper;


import formacion.block17springbatch.tiemporiesgo.TiempoRiesgoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRiesgoDtoRowMapper implements RowMapper<TiempoRiesgoDTO> {
    @Override
    public TiempoRiesgoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiempoRiesgoDTO tiempoRiesgoDTO = new TiempoRiesgoDTO();
        tiempoRiesgoDTO.setCiudad(rs.getString("ciudad"));
        tiempoRiesgoDTO.setNumeroAno(rs.getInt("y"));
        tiempoRiesgoDTO.setNumeroMes(rs.getInt("m"));
        tiempoRiesgoDTO.setNumeroTemperaturas(rs.getInt("c"));
        tiempoRiesgoDTO.setTemperaturaMedia(rs.getFloat("average"));
        return tiempoRiesgoDTO;
    }
}
