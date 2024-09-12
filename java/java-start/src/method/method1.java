package method;

public class method1 {
    public static void main(String[] args) {

        int a = 1, b = 2, c = 3;

        double d = 4.0, e = 3.0, f = 5.0;

        add(a, b, c);
        add(d, e, f);
    }

    public static int add(int a, int b, int c) {

        int sum = a + b + c;
        System.out.println(sum);
        return sum;

    }

    public static double add(double a, double b, double c) {

        double sum = a + b + c;
        System.out.println(sum);
        return sum;

    }

}












