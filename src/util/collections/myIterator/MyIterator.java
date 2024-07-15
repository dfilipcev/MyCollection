package util.collections.myIterator;

import java.util.Objects;
import java.util.function.Consumer;

public interface MyIterator<T> {

    boolean hasNext();

    T next();

    default void forEachRemaining(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}