package chompressive.ch20;

import java.util.*;

public class TestArrayAndLinkedList {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(0, 10);
        arrayList.add(3, 30);

        for (Integer integer : arrayList)
            System.out.print(integer + " ");


        System.out.println();
        LinkedList<Object> linkedList = new LinkedList<>();


        linkedList.add("red");
        linkedList.add(1, "blue");
        linkedList.removeLast();
        linkedList.addFirst("green");

        ListIterator<Object> listIterator = linkedList.listIterator();

        while (listIterator.hasNext())
            System.out.print(listIterator.next() + " ");
        while (listIterator.hasPrevious())
            System.out.println(listIterator.previous());
    }
}