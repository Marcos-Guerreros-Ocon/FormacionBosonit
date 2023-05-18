package formacion;

public class Person {
    private String name;
    private String town;
    private byte age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String name = getName();
        String town = getTown().equals("") ? "unknown" : getTown();
        String age = getAge() == 0 ? "unknown" : String.valueOf(getAge());

        return "Name: " + name + ". Town: " + town + ". Age: " + age;
    }
}
