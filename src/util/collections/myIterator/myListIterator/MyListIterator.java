package util.collections.myIterator.myListIterator;

import util.collections.myIterator.MyIterator;

public interface MyListIterator<T> extends MyIterator<T> {

    boolean hasPrevious();

    T previous();

    int nextIndex();

    int previousIndex();

}
