package chompressive.ch18;

public class Ex18_8_9_10 {

    public static void main(String[] args) {
        reverseDisplay(123);
        System.out.println();
        reverseDisplay("bac");
        System.out.println();
        System.out.println(count("axxxxa",'a'));
    }


    public static void reverseDisplay(int value) {

        if (value / 10 != 0) {
            System.out.print(value % 10);
            reverseDisplay(value / 10);
        } else
            System.out.print(value % 10);
    }

    public static void reverseDisplay(String value) {

        if(value.length() > 0) {
            System.out.print(value.charAt(value.length()-1));
            reverseDisplay(value.substring(0,value.length()-1));

        }
    }

    public static int count(String str, char a) {

        if(str.length() == 0)
            return 0;

        if(str.charAt(str.length()-1) == a)
            return 1+ count(str.substring(0, str.length()-1),a);
        else
            return count(str.substring(0, str.length()-1),a);

    }
}
