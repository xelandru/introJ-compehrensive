package ch19;

import ch13.Circle;

import java.util.ArrayList;


public class DistinctElements {

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        ArrayList<E> nonDuplicates = new ArrayList<>();
        for (E item:list)
            if(!nonDuplicates.contains(item))
                nonDuplicates.add(item);
        return nonDuplicates;
    }

    public static void main(String[] args) {
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(1));
        list.add(new Circle(2));
        list.add(new Circle(2));
        list.add(new Circle(1));
        list.add(new Circle(2));
        list.add(new Circle(0));

        for(Circle i: removeDuplicates(list))
            System.out.println(i);
    }
}
