package array;

import java.util.Scanner;

public class arr5 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("입력받을숫자의 개수를 입력:");
        int n=scanner.nextInt();
        int[] arr=new int[n];
        int sum=0;
        System.out.println(n+"개의 정수를 입력");
        for(int i=0;i<arr.length;i++){

            arr[i]=scanner.nextInt();
            sum+=arr[i];
        }
        System.out.println(sum);
        System.out.println((double)sum/n);
    }
}
