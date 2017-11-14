package ch24;


import java.util.ArrayList;

public class TestMyList {
    public static void main(String[] args) {

        String[] array1 = {"Tom", "George", "Peter", "Jean", "Jane"};
        String[] array2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
        MyArrayList<String> list1 = new MyArrayList<>(array1);
        MyArrayList<String> list2 = new MyArrayList<>(array2);

        System.out.println(list1.retainAll(list2));
        System.out.println(list1);


    }
}
