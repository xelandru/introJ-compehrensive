package chompressive.ch19;

import java.util.ArrayList;

/**
 * Ex 19.9
 */
public class GenericSelectionSort {

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {


        for(int i=0;i<list.size()-1;i++) {
            E currentMin = list.get(i);
            int currentMinIndex =i;

            for(int j=i+1;j<list.size();j++) {
                if(currentMin.compareTo(list.get(j)) == 1) {
                    currentMin = list.get(j);
                    currentMinIndex = j;
                }
            }

            if(currentMinIndex != i) {
                list.set(currentMinIndex,list.get(i));
                list.set(i, currentMin);
            }
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(0);
        array.add(7);
        array.add(-1);


        sort(array);
        for(Integer i: array)
            System.out.println(i);
    }
}
