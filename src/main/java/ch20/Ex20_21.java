package ch20;

import ch13.Circle;
import ch13.GeometricObject;
import ch13.Rectangle;

import java.util.Comparator;

/**
 * Ex. 20.21
 */
public class Ex20_21 {

    public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {

        for (int i = 0; i < list.length - 1; i++) {
            E currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {

                if (comparator.compare(currentMin, list[j]) > 0) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    public static void main(String[] args) {

        GeometricObject[] list = {
                new Circle(5), new Rectangle(4, 5),
                new Circle(5.5), new Rectangle(4.3, 1),
                new Circle(4.6), new Rectangle(4.2, 5.5),
                new Circle(5.2), new Rectangle(0.2, 0.5),
        };

        selectionSort(list, new GeometricObjectComparator());
        for (GeometricObject object: list)
            System.out.println(object);
    }
}
