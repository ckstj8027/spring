package scanner.ex;

import java.util.Scanner;

public class ex7 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        /*이름을 입력하세요 (종료를 입력하면 종료): 자바
            나이를 입력하세요: 30
            입력한 이름: 자바, 나이: 30
            이름을 입력하세요 (종료를 입력하면 종료): 하니
            나이를 입력하세요: 20
            입력한 이름: 하니, 나이: 20
            이   름을 입력하세요 (종료를 입력하면 종료): 종료
            프로그램을 종료합니다. */

        while (true){
            System.out.print("이름을 입력하세요:");
            String name=scanner.nextLine();
            if(name.equals("종료")){
                System.out.println("시스템을 종료합니다");
                break;
            }

            System.out.print("나이을 입력하세요:");
            int age=scanner.nextInt();
            scanner.nextLine();

            System.out.println("입력한 이름:"+ name +"  ,나이:"+ age);


        }
    }
}
