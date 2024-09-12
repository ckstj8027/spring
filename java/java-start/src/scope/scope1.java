package scope;

public class scope1 {
    public static void main(String[] args) {
        long i=2147483647;
        long p=2147483648L;

        System.out.println(i+"asdsadsa"+p);

        int a=(int) i;
        System.out.println(a);

        int b=(int) p;
        System.out.println(b);
    }
}
