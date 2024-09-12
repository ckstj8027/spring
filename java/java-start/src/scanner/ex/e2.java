package scanner.ex;
import java.util.Scanner;
public class e2 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);

        int b=scanner.nextInt();




        f(b);
    }



    public static void f(int a){
        if (a%2==0){
            System.out.println("입력한숫자"+ a+" 은 짝수입니다");

        }
        else{
            System.out.println("입력한숫자"+a +" 은 홀수입니다");

        }
    }
}
