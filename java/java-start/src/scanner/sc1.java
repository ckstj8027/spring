package scanner;

import java.sql.SQLOutput;
import java.util.Scanner;

public class sc1 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("문자열을 입력ㅅ헤요");
        String str=scanner.nextLine();
        System.out.println("푸헷푸헷"+str);

        System.out.println("정수를 입력하세여");
        int i=scanner.nextInt();
        System.out.println("정수를 입력"+i);

        System.out.println("실수를 입력하세여 ");
        double d=scanner.nextDouble();
        System.out.println(d);

    }
}
