package method;

public class method {
    public static void main(String[] args) {
        int money=10000;

        money=gm(money,1000);

        money=pm(money,12000);

        money=gm(money,1000);



    }
    public static int gm(int money,int a){
        money +=a;


        System.out.println(a+" 원 입금되었다");

        System.out.println("남은잔고는  "+money  + "원 이다");


        return money;
    }

    public static int pm(int money,int a){

        if(money>=a) {
            money -=a;

            System.out.println(a + " 원 출금되었다");

            System.out.println("남은잔고는  " + money + "원 이다");

        }
        else{
            System.out.println("잔액이 부족합니다 ");
           // System.out.println("남은잔고는  " + money + "원 이다");

            System.out.println("남은잔고는  " + money + "원 이다");

        }
        return money;
    }



}
