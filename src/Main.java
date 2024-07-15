import MyCollection.MyList.MyArrayList.MyArrayList;
import MyCollection.MyList.MyList;

import util.collections.MyCollections;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        MyList<Integer> general = new MyArrayList<>();

        for (int i = 1; i < 10; i++) {
            general.add(i);
        }

        general.addFirst(0);
        System.out.println(general.get(0) + " first element");

        general.addLast(10);
        System.out.println(general.get(general.size() - 1) + " last element");

        System.out.println(general + " after adding first and last elements");

        general.add(18, 1);
        System.out.println(general.get(1) + " added element");

        System.out.println(general + " after adding 18 at 1st index");

        System.out.println(general.removeFirst() + " removed first element");

        System.out.println(general + " after removing first element");

        MyList<Integer> another = new MyArrayList<>(5);

        for (int i = 0; i < 10; i++) {
            int number = ThreadLocalRandom.current().nextInt(0, 100);
            another.add(number);
        }

        System.out.println(another + " another list after filling");

        System.out.println(general.removeAll(another) + " trying to remove all elements of another list form general list");

        System.out.println(general + " general list after removing all elements from another list");

        System.out.println(general.addAll(another) + " trying to add elements of another list to general list" );

        System.out.println(general + " general list after adding all elements from another list");

        MyCollections.sort(general);

        System.out.println(general + " general list after sorting");
    }
}