package array;

import java.util.Scanner;

public class arr3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m=scanner.nextInt();
        int[] arr=new int[m];
        for(int i=0;i<m  ;i++){
            arr[i]=scanner.nextInt();

        }
        for(int j=0;j<m  ;j++){

            System.out.print(arr[j]);
            if(j<m-1){System.out.print(",");}

        }
        System.out.println();
        System.out.println("......................");
        for(int p=m-1;p>=0  ;p--){

            System.out.print(arr[p]);
            if(p>0){System.out.print(",");}

        }

    }
}
