package stream.zadanie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Box<T> implements Serializable {

    private final List<T> sourceList = new ArrayList<>();
    private final Predicate<T> isValid;

    public Box(Predicate<T> isValid) {
        this.isValid = isValid;
    }

    void addElement(T element) {
        if (isValid.test(element)) {
            sourceList.add(element);
        }
    }

    public List<T> getSourceList() {
        return sourceList;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    public static <T> Box<T> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Box<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }
    }
}
