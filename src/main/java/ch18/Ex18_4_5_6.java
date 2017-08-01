package ch18;


public class Ex18_4_5_6 {

    public static void main(String[] args) {

        System.out.println(sum4(4));
        System.out.println(sum5(2));
        System.out.println(sum6(2));
    }

    public static double sum4(int i){

        if(i == 0)
            return 0;

        else return 1.0/i + sum4(i-1);
    }

    public static double sum5(double i) {

        if(i == 0)
            return 0;

        else
            return i/(2*i+1) + sum5(i-1);
    }
    public static double sum6(double i) {

        if(i == 0)
            return 0;

        else
            return i/(i+1) + sum6(i-1);
    }

}
