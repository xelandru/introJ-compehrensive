package ch13;

/**
 * Created by alex on 6/30/17.
 */
public class Triangle extends GeometricObject {

    private double side1;
    private double side2;
    private double side3;


    public Triangle(double side1, double side2, double side3 , String color, boolean filled) {

        super(color, filled);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    private double semiPerimeter() {

        return (side1+side2+side3)/2;
    }
    @Override
    public double getArea() {

        double p = semiPerimeter();
        return Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));

    }

    @Override
    public double getPerimeter() {

    return 2*semiPerimeter();
    }


    public static void main(String[] args) {
        Triangle t= new Triangle(24,30,18,"blue",true);
        System.out.println(t.getArea());
        System.out.println(t.getPerimeter());
        System.out.println(t.isFilled());
        System.out.println(t.getColor());
    }

    @Override
    public void howToColor() {

        System.out.println("Color all three sides" );
    }
}
