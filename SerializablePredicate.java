package stream.zadanie;

import java.io.Serializable;
import java.util.function.Predicate;

@FunctionalInterface
interface SerializablePredicate<T> extends Predicate<T>, Serializable {
}
