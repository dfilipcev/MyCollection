package dfilipcev.aston.myCollection.util.myIterator.myListIterator;

import dfilipcev.aston.myCollection.util.myIterator.MyIterator;

public interface MyListIterator<T> extends MyIterator<T> {

    boolean hasPrevious();

    T previous();

    int nextIndex();

    int previousIndex();

}
