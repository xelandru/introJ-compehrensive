package chompressive.ch20;

import java.util.Comparator;

/**
 * Created by alex on 7/7/17.
 */
public class CompareY implements Comparator<Point> {


    @Override
    public int compare(Point o1, Point o2) {
        if(o1.getX() == o2.getX())
            return compare(o1.getY(), o2.getY());

        return compare(o1.getX(), o2.getX());
    }



    private int compare(int n1, int n2) {
        if(n1<n2)
            return -1;
        else if(n1>n2)
            return 1;
        else
            return 0;
    }




}
