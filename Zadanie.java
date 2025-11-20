package stream.zadanie;

import java.io.Serializable;
import java.util.function.Predicate;

class Zadanie {
    public static void main(String[] args) {
        /*
        Stworz klase Box, ktora jest generykiem i przechowuje liste elementow generycznych.
        W konstruktorze powinnismy moc dodac warunek od ktorego uzaleznione bedzie dodawanie produktow do Boxa - czyli jak spelnia warunek to dodaje,
        a jak nie to nie.
        -> dodatkowo metoda dodawajaca obiekty
        -> dodatkowo metoda, ktora zapisuje CALOSC Boxa do pliku
        tak samo metoda odczytujaca czyli np loadFromFile
         */

        String filenamePeople = "src/stream/zadanie/people.txt";
        String filenameCar = "src/stream/zadanie/car.txt";

        // box1
        Box<Person> box1 = new Box<>((Predicate<Person> & Serializable) person -> person.age() > 30);

        box1.addElement(new Person("Albert", 28));
        box1.addElement(new Person("Jan", 33));
        System.out.println(box1.getSourceList());

        // box2
        Box<Car> box2 = new Box<>((Predicate<Car> & Serializable) car -> car.model().equals("X5"));

        box2.addElement(new Car("BMW", "X3"));
        box2.addElement(new Car("BMW", "X5"));
        System.out.println(box2.getSourceList());

        // saveToFile
        box1.saveToFile(filenamePeople);
        box2.saveToFile(filenameCar);

        // loadFromFile -> box3 <Person>
        Box<Person> box3 = Box.loadFromFile(filenamePeople);
        System.out.printf("Load from file -> %s%n", box3.getSourceList());

        box3.addElement(new Person("Tomasz", 20));
        box3.addElement(new Person("Olek", 40));
        System.out.println(box3.getSourceList());

        // loadFromFile -> box4 <Car>
        Box<Car> box4 = Box.loadFromFile(filenameCar);
        System.out.printf("Load from file -> %s%n", box4.getSourceList());

        box4.addElement(new Car("BMW", "M8"));
        box4.addElement(new Car("BMW", "X5"));
        System.out.println(box4.getSourceList());
    }

}
