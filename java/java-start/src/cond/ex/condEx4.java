package cond.ex;

public class condEx4 {
    public static void main(String[] args) {
        String a = "어바웃타임";
        String b = "토이스토리";
        String d = "고질라";
        double rating=9;
        if (rating>=7){
            System.out.println("추천한다 "+d);
        }
        if(rating>=8){
            System.out.println("추천한다 "+b);
        }
        if(rating>=9){
            System.out.println("추천한다 "+a);}

    }
}
