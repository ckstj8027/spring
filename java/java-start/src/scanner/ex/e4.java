package scanner.ex;

import java.util.Scanner;

public class e4 {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.print("단을 입력해주세요:  ");
        int b=scanner.nextInt();

        f(b);

    }


    public static void f(int a){
        for(int i=1;i<=9 ;i++){
            System.out.println(a +"*"+i+"="+(a*i)+"입니다");
        }
    }

}








