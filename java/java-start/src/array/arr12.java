package array;

import java.util.Scanner;

public class arr12 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Object[][] a=new Object[10][2];


        int count=0;


        while (true){
            System.out.println("1상품등록 2상품목록 3종료");
            int r=scanner.nextInt();
            scanner.nextLine();

            if (r==1){
                if(count<=9){
                    System.out.print("상품이름:");
                    a[count][0]=scanner.nextLine();
                    System.out.print("상품가격:");
                    a[count][1]=scanner.nextInt();
                    scanner.nextLine();
                    count++;}
                else{
                    System.out.println("등록초과입니다");
                }





            }




            else if(r==2){

                for(int i=0;i<a.length ;i++){
                    if(a[i][0]!=null){

                        System.out.println(a[i][0]);
                        System.out.println(a[i][1]);

                    }
                }


            }





            else{
                break;
            }
        }

    }
}
