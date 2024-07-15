package MyCollection.myIterable;

import util.collections.myIterator.MyIterator;

import java.util.function.Consumer;

public interface MyIterable<T> {

    MyIterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        if (action == null) {
            return;
        }
        MyIterator<T> iterator = iterator();
        while(iterator().hasNext()) {
            action.accept(iterator.next());
        }
    }
}
