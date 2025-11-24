package stream.zadanie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Box<T extends Serializable> implements Serializable {

    private final List<T> sourceList = new ArrayList<>();
    private final SerializablePredicate<T> isValid;

    public Box(SerializablePredicate<T> isValid) {
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
            throw new BoxSerializationException("Failed to save Box to file: " + filename, e);
        }
    }

    public static <T extends Serializable> Box<T> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Box<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BoxDeserializationException("Failed to load the box from file: " + filename, e);
        }
    }
}
