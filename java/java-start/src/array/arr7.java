package array;

import java.util.Scanner;

public class arr7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("입력받을 숫자의 개수를 입력하세요:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int max ;
        int min ;
        System.out.println(n + "개의 정수를 입력하세요:");
        for (int i = 0; i <= arr.length - 1; i++) {
            arr[i] = scanner.nextInt();
        }
        min=arr[0];
        max=arr[0];
        for (int j = 0; j<=arr.length - 1; j++){


            if(arr[j]<=min){
                min=arr[j];   }

            if (arr[j] >= max) {
                max = arr[j];


            }
        }
        System.out.println("가장작은정수: " + min);
        System.out.println("가장 큰 정수: " + max);

    }
}

