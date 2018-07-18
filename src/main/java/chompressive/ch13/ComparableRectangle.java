package chompressive.ch13;



public class ComparableRectangle  extends Rectangle implements Comparable<ComparableRectangle> {

    public ComparableRectangle(double width, double height) {
        super(width, height);
    }

    public int compareTo(ComparableRectangle o) {


        if(this.getArea() < o.getArea())
            return -1;
        else if(this.getArea() == o.getArea())
            return 1;
        else
            return 0;
    }

    public String toString() {
        return super.toString() + " Area: " + getArea();
    }
}
