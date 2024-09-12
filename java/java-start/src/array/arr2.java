package array;

import java.util.Scanner;

public class arr2 {
    public static void main(String[] args) {


        int[] student=new int[]{1,2,3,4,5,6,7};

        for(int i=0 ;i<=student.length-1  ; i++){
            student[i]=i;
        }

        for(int j=0;j<=student.length-1;j++){
            System.out.println(student[j]);
        }

    }
}
