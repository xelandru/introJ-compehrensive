package ch20;

import java.util.LinkedList;
import java.util.List;

/**
 * Ex 20.2
 */
public class StoreNumbers {

    public static List<Number> numbers = new LinkedList<>();
    public static void store(Number number) {
        if (!numbers.contains(number))
            numbers.add(number);
    }

    public static void main(String[] args) {
        StoreNumbers.store(2);
        StoreNumbers.store(3);
        StoreNumbers.store(3);
        StoreNumbers.store(2.3);
        StoreNumbers.store(2.3);
        System.out.println(numbers);
    }

}
