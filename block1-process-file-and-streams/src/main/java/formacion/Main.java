package formacion;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Person> personas = leerFichero("people.csv");
        List<Person> personasFiltradasEdad;
        List<Person> personasFiltradasNombre;
        Stream<Person> personStream;

        Predicate<Person> filtroEdad = person -> person.getAge() > 25;
        Predicate<Person> filtroNombre = person -> person.getName().charAt(0) == 'A';

        System.out.println("TODAS LAS PERSONAS");
        mostrarPesonas(personas);

        System.out.println("\nFILTRO POR EDAD");
        personasFiltradasEdad = filtrarPersonas(personas, filtroEdad);
        mostrarPesonas(personasFiltradasEdad);

        System.out.println("\nFILTRO POR NOMBRE");
        personasFiltradasNombre = filtrarPersonas(personas, filtroNombre);
        mostrarPesonas(personasFiltradasNombre);

        personStream = personasFiltradasEdad.stream();
        System.out.println("\nPRIMERA PERSONA MAYOR DE 25 AÑOS DE MADRID");
        personStream.filter(person -> person.getTown().equals("Madrid")).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("NO SE HAN ENCONTRADO RESULTADOS"));

        personStream = personasFiltradasEdad.stream();
        System.out.println("\nPRIMERA PERSONA MAYOR DE 25 AÑOS DE BARCELONA");
        personStream.filter(person -> person.getTown().equals("Barcelona")).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("NO SE HAN ENCONTRADO RESULTADOS"));


    }

    private static List<Person> leerFichero(String nombreFichero) {
        List<Person> personas = new ArrayList<>();
        String linea;
        String[] datos;
        Person person;
        BufferedReader leer;
        try {
            leer = new BufferedReader(new FileReader(new File(nombreFichero)));

            linea = leer.readLine();
            while (linea != null) {
                datos = linea.split(":");
                person = new Person();
                person.setName(datos[0]);
                person.setTown(datos[1]);
                person.setAge(Byte.parseByte(datos[2]));
                personas.add(person);
                linea = leer.readLine();
            }

            return personas;

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer");
            throw new RuntimeException(e);
        }

        return personas;
    }

    private static List<Person> filtrarPersonas(List<Person> personList, Predicate<Person> filter) {
        Stream<Person> streamPersonas = personList.stream();
        return streamPersonas.filter(filter).toList();
    }


    private static void mostrarPesonas(List<Person> personList) {
        personList.forEach(System.out::println);
    }

}