package method;

import java.util.Scanner;

public class method5 {
    public static void main(String[] args) {

        int balance = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------------");


            int choice = scanner.nextInt();
            int amount;

            switch (choice) {

                case 3:
                    System.out.println("현재 잔액: " + balance + "원");
                    break;
                case 4:
                    System.out.println("시스템을 종료합니다.");
                    break;
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택해주세요.");
            }

        }

    }}

