package Operator;

public class OperatorAdd2 {
    public static void main(String[] args) {
        //전위 증감 연산자
        int a=1;
        int b=0;
        b=++a;
        System.out.println("a="+a+",b="+b);
        //후위
        int c=1;
        int d=0;
        d=c++;
        System.out.println("c="+c+",d="+d);
    }
}
