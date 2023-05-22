package formacion.block6personcontrollers.models;

public class CityModel {
    private String name;
    private int population;

    public CityModel() {

    }

    public CityModel(String name, int population) {
        setName(name);
        setPopulation(population);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
