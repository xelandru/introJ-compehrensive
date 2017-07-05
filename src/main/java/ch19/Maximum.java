package ch19;

/**
 * Ex: 19.5
 */
public class Maximum {

    public static <E extends Comparable<E>> E max(E[] list) {
        E max = list[0];
        for(E element: list)
            if(element.compareTo(max) == 1 )
                max = element;
        return max;
    }

    public static void main(String[] args) {
        Integer[] integers = {3,0,3,-1,7};
        System.out.println(max(integers));
    }
}
