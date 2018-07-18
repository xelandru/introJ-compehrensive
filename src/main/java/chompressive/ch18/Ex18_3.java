package chompressive.ch18;

public class Ex18_3 {
    public static int gcd(int m, int n) {

        if(m%n == 0)
            return n;
        else
           return gcd(n, m%n);
    }

    public static void main(String[] args) {
        System.out.println(gcd(7,11));
    }
}
