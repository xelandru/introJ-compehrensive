package ch19;

import java.util.ArrayList;

/**
 * Ex 19.7
 */
public class GenericBinarySearch {

    public static <E extends Comparable<E>>  int binarySearch(E[] list, E key) {

        int high = list.length-1;
        int low = 0;

        while (low <= high) {

            int mid = (high+low)/2;

            if(key.compareTo(list[mid]) == -1) {
                high = mid - 1;
            }
            else if (key.compareTo(list[mid]) == 1) {
                low = mid + 1;
            }
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] list= {0,7,8,11,17,19};
        Integer key = -100;
        System.out.println(binarySearch(list,key));
    }
}
