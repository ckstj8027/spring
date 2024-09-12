package scanner.ex;

import java.util.Scanner;

public class e5 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] k=new int[2];
        System.out.print("a 를 입력하세요:");
        k[0]=scanner.nextInt();
        System.out.print("b 를 입력하세요:");
        k[1]=scanner.nextInt();

        swap(k);
        System.out.println(k[0]);
        System.out.println(k[1]);





    }

    public static void swap(int[] k){
        int temp=0;
        temp=k[0];
        k[0]=k[1];
        k[1]=temp;



    }
}
