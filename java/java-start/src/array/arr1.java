package array;

import java.util.Scanner;

public class arr1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();

        int[] student=new int[n];

        for(int i=0 ;i<=student.length-1  ; i++){
            student[i]=i;
        }

        for(int j=0;j<=student.length-1;j++){
            System.out.println(student[j]);
        }

    }
}
