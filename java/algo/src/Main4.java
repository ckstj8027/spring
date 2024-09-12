import java.io.*;
import java.util.*;
import java.lang.*;

public class Main4 {
    static List<List<Integer>> d;
    static int n;
    static int m;

    static boolean[][] visited ;
    static int[][] grid;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());



        grid = new int[n][m];





        for (int i = 0; i < n; i++) {
            String a = br.readLine();
            for (int j = 0; j < m; j++) {
                int b = Integer.parseInt(String.valueOf(a.charAt(j)));
                grid[i][j] = b;


            }


        }

        }



}


