
package beak.Main;
import java.io.*;
import java.util.*;
import java.lang.*;



import java.io.*;
import java.util.*;

public class Main {
    //Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 25);

        //  Map<Integer,Integer> memo=new HashMap<>();


        // StringTokenizer st=new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        int[] c=new int[n];

        Arrays.fill(c,1);


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;

        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));


        for (int i = 0; i < n; i++) {

          //  System.out.print(arr[i][0]+" ");
          //  System.out.print(arr[i][1]+" ");
           // System.out.println(" ");
            }


        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (arr[i][1] >= arr[j][1]) {
                    c[i] = Math.max(c[i], c[j] + 1);
                }
            }



        }

        int max=0;
        for (int i = 0; i < n; i++) {
            if(max<c[i]){
                max=c[i];
            }


        }
        System.out.println(n-max);
















        }











    }

























































