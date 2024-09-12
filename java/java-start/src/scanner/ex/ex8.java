package scanner.ex;

import java.util.Scanner;

public class ex8 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int sum=0;
        int count=0;

        while(true){
            System.out.print("숫자를 입력하세요. 입력을 중단하려면 -1을 입력하세요:");

            int a=scanner.nextInt();



            if(a==-1){

                break;
            }
            else{
                sum+=a;
                count++;

            }

        }
        System.out.println("입력한 숫자합:"+sum);
        System.out.println("입력한 숫자 평균:"+ (double)sum/count);
    }
}
