package ch20;

import ch13.Circle;
import ch13.GeometricObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GeometricObjectComparator implements Comparator<GeometricObject>, java.io.Serializable {

	public int compare(GeometricObject o1, GeometricObject o2) {

		if(o1.getArea() < o2.getArea())
			return -1;
		else if(o1.getArea() == o2.getArea())
			return 0;
		else
			return 1;
	}

    public static void main(String[] args) {
        GeometricObject g1 = new Circle(1);
        GeometricObject g2 = new Circle(2);
        GeometricObjectComparator comparator = new GeometricObjectComparator();
        GeometricObject[] array = {g2, g1};
        Arrays.sort(array);
        for(GeometricObject g: array)
            System.out.println(g);

        List<GeometricObject> list = new ArrayList<>();
        list.add(g2);
        list.add(g1);
        list.sort(comparator);
        for(GeometricObject g:list)
            System.out.println(g);
    }
}
