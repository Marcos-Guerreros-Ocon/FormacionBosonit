package formacion.block6simplecontrollers.models;

public class PersonModel {

    private String name;
    private String city;
    private int age;


    public PersonModel() {

    }

    public PersonModel(String name, String city, int age) {
        setName(name);
        setCity(city);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
