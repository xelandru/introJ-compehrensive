package ch19;

/**
 * Ex 19.4
 */
public class GenericLinearSearch {

    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {

        for(int i =0 ;i< list.length;i++)
            if(list[i].compareTo(key) == 0)
                return i;
        return -1;

    }

    public static void main(String[] args) {
        Integer[] list = {1,3,-2,7};
        Integer key = -2;
        System.out.println(linearSearch(list, key));
    }
}
