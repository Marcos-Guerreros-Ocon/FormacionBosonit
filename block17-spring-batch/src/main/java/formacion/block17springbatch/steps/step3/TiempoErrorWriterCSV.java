package formacion.block17springbatch.steps.step3;



import formacion.block17springbatch.domain.TiempoError;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TiempoErrorWriterCSV implements ItemWriter<TiempoError> {

    @Override
    public void write(List<? extends TiempoError> list) throws IOException {

        FileWriter fileWriter = new FileWriter("src/main/resources/REGISTROS_ERRONEOS.csv", true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (TiempoError tiempoError : list) {
            fileWriter.append(tiempoError.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(dateFormat.format(tiempoError.getFecha()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoError.getTemperatura()));
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }


}