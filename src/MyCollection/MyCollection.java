package MyCollection;

import MyCollection.myIterable.MyIterable;

import java.util.Collection;

public interface MyCollection<E> extends MyIterable<E> {

    boolean add(E e);

    boolean addAll(MyCollection<? extends E> c);

    boolean remove(Object o);

    boolean removeAll(MyCollection<? extends E> c);

    boolean contains(E e);

    int size();

    boolean isEmpty();

    E[] toArray();

}
