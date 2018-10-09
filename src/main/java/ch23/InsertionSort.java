package ch23;


import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] list) {

        int tmp;

        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j > 0 && list[j] < list[j - 1]; j--) {
                tmp = list[j - 1];
                list[j - 1] = list[j];
                list[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {

        int[] list = {9,2,1,7,8,3,-1,1,0};
        insertionSort(list);
        System.out.println(Arrays.toString(list));
    }


}


