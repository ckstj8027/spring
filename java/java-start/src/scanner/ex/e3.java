package scanner.ex;

import java.util.Scanner;

public class e3 {
    public static void main(String[] args) {


        /*  음식 이름을 입력해주세요: 피자
            음식의 가격을 입력해주세요: 20000
            음식의 수량을 입력해주세요: 2
            피자 2개를 주문하셨습니다. 총 가격은 40000원입니다.
        *
        *
        *
        *
        *      */

        Scanner scanner=new Scanner(System.in);
        System.out.print("음식 이름을 입력해주세요:");
        String food=scanner.nextLine();
        System.out.print("음식의 가격을 입력해주세요:");
        int price=scanner.nextInt();
        System.out.print("음식의 수량을 입력해주세요:");
        int n=scanner.nextInt();
        System.out.print(food +"2개를 주문하셨습니다. 총 가격은"+ (price*n)+"원입니다.");
    }


}
