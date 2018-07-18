package ch13;


public class Complex  implements Cloneable{

    private double a;
    private double b;

    public Complex() {

    }
    public Complex(double a) {
        this.a = a;

    }
    public Complex(double a, double b) {
        this(a);
        this.b = b;

    }

    public double getImaginaryPart() {

        return b;
    }

    public double getRealPart() {

        return b;
    }

    public Complex add(Complex z){

        double c = this.a + z.a;
        double d = this.b + z.b;

        return new Complex(c,d);



    }

    public Complex subtract(Complex z){

        double c = this.a - z.a;
        double d = this.b - z.b;

        return new Complex(c,d);



    }

    public Complex multiply(Complex z){

        double c = this.a * z.a - this.b * z.b;
        double d = this.b * z.a + this.a * z.b;

        return new Complex(c,d);



    }

    public Complex divide(Complex z) {

        double c = (this.a * z.a + this.b * z.b)/(z.a * z.a + z.b + z.b);
        double d = (this.b * z.a - this.a * z.b)/(z.a * z.a + z.b + z.b);

        return new Complex(c,d);
    }

    public Complex abs() {

        return new Complex(Math.sqrt(a*a + b*b));
    }


    @Override
    public String toString() {

        if(b == 0 )
            return  "" + a;

        return "(" + a + " + " + b + "i" + ")";
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Complex z1 = new Complex(3,4);
        Complex z2 = (Complex) z1.clone();

        System.out.println("Sum:        " + z1.add(z2));
        System.out.println("Difference: " + z1.subtract(z2));
        System.out.println("Multiply:   " + z1.multiply(z2));
        System.out.println("Divide:     " + z1.divide(z2));
        System.out.println("Absolute:   " + z1.abs());
        System.out.println("Absolute:   " + z2.abs());

    }



}

