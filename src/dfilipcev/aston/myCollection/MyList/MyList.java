package dfilipcev.aston.myCollection.MyList;

import dfilipcev.aston.myCollection.MyCollection;
import dfilipcev.aston.myCollection.util.myIterator.myListIterator.MyListIterator;

public interface MyList<E> extends MyCollection<E> {

    E get(int index);

    boolean add(E e, int index);

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean addAll(MyCollection<? extends E> c);

    E remove(int index);

    boolean remove(Object o);

    E removeFirst();

    E removeLast();

    boolean removeAll(MyCollection<? extends E> c);

    E set(int index, E element);

    boolean contains(E e);

    int indexOf(Object element);

    int size();

    boolean isEmpty();

    E[] toArray();

    MyListIterator<E> listIterator();

    MyListIterator<E> listIterator(int index);
}