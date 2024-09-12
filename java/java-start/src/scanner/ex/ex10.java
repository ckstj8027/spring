package scanner.ex;

import java.util.Scanner;

public class ex10 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int price=0;
        while (true){
            System.out.println("1 상품입력 2 결재 3 프로그램 종료");
            int option=scanner.nextInt();
            scanner.nextLine();


            if (option==1){
                System.out.print("상품명을 입력하세요:");
                String name= scanner.nextLine();

                System.out.print("상품 가격을 입력하세요:");
                int value= scanner.nextInt();

                System.out.print("구매 수량을입력하세요:");
                int quantity= scanner.nextInt();

                price+=value * quantity;


            }

            else if (option==2){
                System.out.println(price+" 원 결재 하겠습니다");


            }

            else if (option==3){
                break;


            }


            else{
                System.out.println(" 잘못된 입력입니다 다시 입력해주세요");


            }


        }
    }
}
