package method;

import java.util.Scanner;

public class method4 {
    public static void main(String[] args) {
        // 1 입금 2 추금 3 잔액확인  4종료
        int money=0;

        Scanner scanner=new Scanner(System.in);


        while(true){
            System.out.println("선택하시오");
            System.out.println("1 입금 2 추금 3 잔액확인  4종료");
            int c=scanner.nextInt();
            scanner.nextLine();

            if(c==1){
                System.out.print("입금액을 입력하세요:");
                int w=scanner.nextInt();
                scanner.nextLine();
                money=gm(money,w);
               // System.out.print(w+" 원을 입금했습니다");
               // System.out.print("현재잔액"+money+"원입니다");




            }
            else if(c==2){
                System.out.print("출금액을 입력하세요:");
                int w=scanner.nextInt();
                scanner.nextLine();
                money=pm(money,w);
               // System.out.print(w+" 원을 출금했습니다");
              //  System.out.print("현재잔액"+money+"원입니다");

            }

            else if(c==3){
                cm(money);

            }
            else{
                System.out.println("사용을 종료합니다");
                break;
            }

        }
    }
    public  static int gm(int m,int w){
        m+=w;
        System.out.println(w+" 원 입금 되었습니다"+" 따라서 총액: "+m+"원");
        return  m;

    }

    public  static int pm(int m,int w){

        if(m>=w){
            m-=w;
            System.out.println(w+" 원 출금 되었습니다"+" 따라서 총액: "+m+"원");
            return  m;}
        else{
                System.out.println("잔액 딸려서 출금 못함");
            return m;
            }



    }

    public  static void cm(int money){
        System.out.println(money+" 원남았습니다 ");





    }




}
