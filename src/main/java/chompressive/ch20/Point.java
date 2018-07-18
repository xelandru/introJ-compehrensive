package chompressive.ch20;



/**
 * 20.4
 */

public class Point implements Comparable<Point>{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if(compare(getX(), o.getX()) == 0)
            return compare(getY(), o.getY());

        return compare(getX(), o.getX());
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
