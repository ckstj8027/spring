package scanner.ex;

import java.util.Scanner;

public class e6 {
    public static void main(String[] args) {

        /*첫 번째 숫자를 입력하세요:2
            두 번째 숫자를 입력하세요:5
            두 숫자 사이의 모든 정수:2,3,4,5    */


        Scanner scanner=new Scanner(System.in);
        System.out.print("첫 번째 숫자를 입력하세요:");
        int a=scanner.nextInt();
        System.out.print("두 번째 숫자를 입력하세요:");
        int b=scanner.nextInt();

        f(a,b);

    }

    public static void f(int a,int b) {

        if (a >= b) {
            for (int i = b; i <= a; i++) {
                System.out.print(i);

                if(i!=a) {
                    System.out.print(",");
                }
            }
        }
        else {
            for (int i = a; i <= b; i++) {

                if(i!=b) {
                    System.out.print(",");
                }



            }


        }
    }}
