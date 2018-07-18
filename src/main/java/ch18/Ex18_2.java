package ch18;


public class Ex18_2 {
    public static int fib(int n) {

        int f0=0;
        int f1=1;
        int currentFib=0;

        for(int i=1;i<=n;i++) {
            currentFib = f0 + f1;
            f0=f1;
            f1=currentFib;
        }

        return currentFib;
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
    }
}
