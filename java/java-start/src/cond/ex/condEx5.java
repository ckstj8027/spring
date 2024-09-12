package cond.ex;

public class condEx5 {
    public static void main(String[] args) {
        String score="A";

        switch (score){
            case "A" -> System.out.println("학점 4.5");
            case "B" -> System.out.println("학점 3.5");
            case "C" -> System.out.println("학점 2.5");
            case "D" -> System.out.println("학점 1.5");
            case "F" -> System.out.println("학점 0.5");
            default -> System.out.println("올바르지 않은 선택입니다.");

        }

    }
}
