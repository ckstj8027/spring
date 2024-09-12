package array;

import java.util.Scanner;

public class arr4 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int[] arr=new int[5];

        for(int i=0;i<=arr.length-1  ; i++){
            arr[i]=scanner.nextInt();

        }

        int sum=0;
        for(int j=0;j<=arr.length-1  ; j++){
            sum+=arr[j];

        }

        System.out.println(sum);
        System.out.println((double) sum/arr.length);

    }
}
