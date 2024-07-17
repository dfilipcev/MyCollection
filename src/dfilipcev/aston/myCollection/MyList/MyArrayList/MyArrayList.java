package dfilipcev.aston.myCollection.MyList.MyArrayList;

import dfilipcev.aston.myCollection.MyCollection;
import dfilipcev.aston.myCollection.MyList.MyList;
import dfilipcev.aston.myCollection.exceptions.emptyCollectionOperation.emptyListOperation.EmptyListOperation;
import dfilipcev.aston.myCollection.util.myIterator.MyIterator;
import dfilipcev.aston.myCollection.util.myIterator.myListIterator.MyListIterator;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTS_DATA = new Object[0];

    private Object[] elementsData;
    private int size;

    private static final int BIG_DATA = 1300;

    public MyArrayList() {
        elementsData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementsData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementsData = EMPTY_ELEMENTS_DATA;
        } else {
            throw new NegativeArraySizeException("Negative capacity: " + initialCapacity);
        }
        size = 0;
    }

    public MyArrayList(MyCollection<? extends E> elements) {
        if (elements == null || elements.isEmpty()) {
            elementsData = EMPTY_ELEMENTS_DATA;
            size = 0;
        } else {
            E[] elementsArray = elements.toArray();
            elementsData = Arrays.copyOf(elementsArray, size = elementsArray.length, Object[].class);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean remove(Object element) {
        if (isEmpty()) {
            return false;
        }
        int elementIndex;
        if ((elementIndex = indexOf(element)) < 0) {
            return false;
        }
        removeAt(elementIndex);
        return true;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()) {
            throw new EmptyListOperation();
        }
        E removed = getElement(index);
        removeAt(index);
        return removed;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new EmptyListOperation();
        }
        int lastIndex = size - 1;
        E removed = getElement(lastIndex);
        removeAt(lastIndex);
        return removed;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new EmptyListOperation();
        }
        int firstIndex = 0;
        E removed = getElement(firstIndex);
        removeAt(firstIndex);
        return removed;
    }

    public boolean removeAll(MyCollection<? extends E> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }
        if (isDataBig()) {
            return bigDataRemoveAll(elements);
        }

        E[] toRemove = elements.toArray();
        int originalSize = size;

        for (E e : toRemove) {
            for (int j = 0; j < size; j++) {
                int newSize;
                if ((elementsData[j] == null && e == null) && (newSize = (size - 1)) > j) {
                    System.arraycopy(elementsData, j + 1, elementsData, j, (size = newSize) - j);
                } else if (elementsData[j] != null && elementsData[j].equals(e)) {
                    removeAt(j);
                }
            }
        }
        return originalSize != size;
    }

    private boolean bigDataRemoveAll(MyCollection<? extends E> elements) {
        E[] toRemove = elements.toArray();
        int originalSize = size;

        int newSize = 0;
        outer:
        for (int i = 0; i < originalSize; i++) {
            for (E removeElement : toRemove) {
                if (elementsData[i] == null && removeElement == null) {
                    continue outer;
                }
                if (elementsData[i] != null && elementsData[i].equals(removeElement)) {
                    continue outer;
                }
            }
            elementsData[newSize++] = elementsData[i];
        }

        for (int i = newSize; i < originalSize; i++) {
            elementsData[i] = null;
        }

        boolean removed = size != newSize;
        size = newSize;
        return removed;
    }

    private void removeAt(int index) {
        int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementsData, index + 1, elementsData, index, newSize - index);
        }
        elementsData[size = newSize] = null;
    }

    @Override
    public boolean addAll(MyCollection<? extends E> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        E[] toAdd = elements.toArray();
        if (elementsData.length < size + toAdd.length) {
            elementsData = Arrays.copyOf(elementsData, elementsData.length + toAdd.length);
        }
        System.arraycopy(toAdd, 0, elementsData, size, toAdd.length);
        size += toAdd.length;
        return true;
    }

    @Override
    public boolean add(E element) {
        add(element, elementsData, size);
        return true;
    }

    @Override
    public boolean add(E element, int index) {
        indexOutOfBoundCheck(index);
        int realElements;
        if ((realElements = size) == elementsData.length) {
            increaseCapacity();
        }
        System.arraycopy(elementsData, index, elementsData, index + 1, realElements - index);
        elementsData[index] = element;
        ++size;
        return true;
    }

    private void add(E element, Object[] elementsData, int index) {
        if (index == elementsData.length) {
            elementsData = increaseCapacity();
        }
        elementsData[index] = element;
        ++size;
    }

    @Override
    public boolean addLast(E element) {
        add(element);
        return true;
    }

    @Override
    public boolean addFirst(E element) {
        int firstIndex = 0;
        if (size == firstIndex) {
            return add(element);
        }
        return add(element, firstIndex);
    }

    @Override
    public E get(int index) {
        return getElement(index);
    }

    @Override
    public E set(int index, E newElement) {
        E oldElement = getElement(index);
        elementsData[index] = newElement;
        return oldElement;
    }

    @SuppressWarnings("unchecked")
    private E getElement(int index) {
        if (elementsData[index] == null) {
            throw new IllegalArgumentException("Element is empty");
        }
        indexOutOfBoundCheck(index);
        return (E) elementsData[index];
    }

    @Override
    public int indexOf(Object element) {
        return indexOfRange(element, 0, size);
    }

    private int indexOfRange(Object element, int fromIndex, int toIndex) {
        if (element == null) {
            for (int i = fromIndex; i < toIndex; i++) {
                if (elementsData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                if (elementsData[i].equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private Object[] increaseCapacity() {
        int newCapacity;
        if (elementsData == EMPTY_ELEMENTS_DATA) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = (size << 1) + 1;
        }
        return elementsData = Arrays.copyOf(elementsData, newCapacity);
    }

    private void indexOutOfBoundCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private boolean isDataBig() {
        return size > BIG_DATA;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elementsData, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public MyIterator<E> iterator() {
        return new Iter();
    }

    public MyListIterator<E> listIterator() {
        return new ArrayListIter();
    }

    public MyListIterator<E> listIterator(int index) {
        return new ArrayListIter(index);
    }

    private class Iter implements MyIterator<E> {

        protected static final int DEFAULT_CURRENT_INDEX = 0;
        protected int current;

        private Iter(){
            current = DEFAULT_CURRENT_INDEX;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            E next = getElement(current);
            current++;
            return next;
        }
    }

    private class ArrayListIter extends Iter implements MyListIterator<E> {

        private ArrayListIter(int current) {
            indexOutOfBoundCheck(current);
            this.current = current;
        }

        private ArrayListIter() {
            super();
        }

        @Override
        public int nextIndex() {
            return current + 1;
        }

        @Override
        public boolean hasPrevious() {
            return current > DEFAULT_CURRENT_INDEX;
        }

        @Override
        public E previous() {
            E previous = getElement(current);
            current--;
            return previous;
        }

        @Override
        public int previousIndex() {
            return current--;
        }

    }
}