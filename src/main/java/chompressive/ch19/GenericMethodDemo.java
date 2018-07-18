package chompressive.ch19;


public class GenericMethodDemo {
    public static void main(String[] args) {

        Integer[] integers = {1, 2, 3};
        String[] strings = {"string1", "string2"};

        GenericMethodDemo.print(integers);
        GenericMethodDemo.print(strings);
    }

    public static <E> void print(E[] list) {

        for (int i = 0; i < list.length; i++)
            System.out.println(list[i]);

    }
}
