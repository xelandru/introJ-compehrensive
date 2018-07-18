package chompressive.ch18;


public class Ex18_22 {

    public static void main(String[] args) {
//        System.out.println(dec2Bin(0));
        System.out.println(dec2Bin(16));
    }

    public static String dec2Bin(int value) {

        if(value/2 == 0)
            return "" + value%2;

        return  dec2Bin(value/2)  + value%2 ;
    }

    public static String dec2Hex(int value) {
        if(value/2 == 0)
            return "" + value%16;

        return  dec2Bin(value/16)  + value%16 ;
    }

}
