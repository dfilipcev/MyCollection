package util.collections;

import MyCollection.MyList.MyList;

import java.util.Comparator;

public class MyCollections {

    private MyCollections() {
    }

    public static <T extends Comparable<? super T>> void sort(MyList<T> list) {
        if (list.isEmpty() || list.size() == 1) {
            return;
        }
        if(isSorted(list)) {
            return;
        }
        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T t = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, t);
                }
            }
        }
    }

    public static <T> void sort(MyList<T> list, Comparator<T> comparator) {
        if (list.isEmpty() || list.size() == 1) {
            return;
        }
        if (isSorted(list, comparator)) {
            return;
        }
        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T t = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, t);
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> boolean isSorted(MyList<T> list) {
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            return true;
        }

        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isSorted(MyList<T> list, Comparator<T> comparator) {
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            return true;
        }

        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            if ((comparator.compare(list.get(i), list.get(i + 1))) > 0) {
                return false;
            }
        }
        return true;
    }

}
