package array;

import java.util.Scanner;

public class arr11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] p_n = new String[10];
        int[] p_p = new int[10];
        String[] s = new String[]{"상품등록", "상품목록", "종료"};


        int count = 0;
        while (true) {
            System.out.println("1"+s[0]+" 2"+s[1]+" 3"+s[2]);

            int a = scanner.nextInt();
            scanner.nextLine();
            if (s[a-1]==(s[0])) {
                if (count<=10){
                    System.out.println("상품명 입력하시오:");
                    p_n[count] = scanner.nextLine();
                    System.out.print("상품가격입력하시오:");
                    p_p[count] = scanner.nextInt();
                    scanner.nextLine();

                }
                else{
                    System.out.println("더이상 등록 불가 ");
                }



                count++;}

            else if (s[a-1]==(s[1])) {


                for (int i = 0; i < 10; i++) {
                    if(p_n[i]!=null){
                        System.out.println("상품이름"+p_n[i]);
                        System.out.println("상품가격"+p_p[i]);

                    }

                    }


            } else {
                break;
            }
        }


    }}
