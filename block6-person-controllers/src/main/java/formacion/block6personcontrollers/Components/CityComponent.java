package formacion.block6personcontrollers.Components;

import formacion.block6personcontrollers.models.CityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityComponent {

    private List<CityModel> cities;

    public CityComponent(){
        cities = new ArrayList<>();
    }

    public boolean addCity(CityModel city){
        return cities.add(city);
    }
    public List<CityModel> getCities() {
        return cities;
    }
}
