package cond.ex;

public class condEx3 {
    public static void main(String[] args) {
        int dollar=5;

        if (dollar==0){
            System.out.println("금액없음");
        }
        else if(dollar>0){
            System.out.println("계삳된금액은"+ dollar*1300  + "원입니다 ");
        }
        else{
            System.out.println("잘못된 값입니다");
        }
    }
}
