package ch19;

import java.util.ArrayList;

/**
 * Ex 19.10
 */
public class MaximumList {

    public static <E extends Comparable<E>> E max(ArrayList<E> list) {

        E max = list.get(0);
        for(E item: list)
            if(item.compareTo(max) == 1)
                max = item;
        return max;
    }

    public static void main(String[] args) {
        ArrayList<Double> doubles= new ArrayList<>();
        doubles.add(0.0);
        doubles.add(0.1);
        doubles.add(-1.1);
        doubles.add(2.1);
        System.out.println(max(doubles));

    }
}
