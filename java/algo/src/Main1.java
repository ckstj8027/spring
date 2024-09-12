import java.io.*;
import java.util.*;
import java.lang.*;

public class Main1 {
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //StringTokenizer st = new StringTokenizer(br.readLine());

       // int n = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());



        dp=new int[n+1];
        Arrays.fill(dp,0);

        dp[0]=0;
        dp[1]=1;

        System.out.println(dfs(n));

        dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        System.out.println(dfs1(n));


    }
    public static int dfs(int n){
        if(n==1||n==0){
            return dp[n];
        }
        else {
            if(dp[n]==0){
                dp[n]=dfs(n-1)+dfs(n-2);
            }
            return dp[n];
        }

    }

    public static int dfs1(int n){
        if(n==1||n==0){
            return dp[n];
        }
        for(int i=2;i<n+1;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];

    }




}


