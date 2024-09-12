import java.io.*;
import java.util.*;
import java.lang.*;

public class Main3 {
    static int[][] memo;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       // StringTokenizer st = new StringTokenizer(br.readLine());

        //int n = Integer.parseInt(st.nextToken());

        //  List<Integer> point=new ArrayList<>();
        //  List<Integer> score=new ArrayList<>();

        n=3;
        m=7;
        memo=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                memo[i][j]=-1;


            }
        }

        System.out.println(dfs(n-1,m-1));

        memo=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                memo[i][j]=-1;


            }
        }
        System.out.println(dfs1(n-1,m-1));



    }

    public static int dfs(int r,int c){

        if(r==0 &&c==0){
            memo[r][c]=1;
            return memo[r][c];
        }
        int cnt=0;

        if(memo[r][c]==-1){
            if(r-1>=0){
                cnt+=dfs(r-1,c);
            }

            if(c-1>=0){
                cnt+=dfs(r,c-1);
            }
            memo[r][c]=cnt;

        }



        return memo[r][c];


    }



    public static int dfs1(int r,int c){


        for(int i=0;i<n;i++){

            memo[i][0]=1;


        }
        for(int i=0;i<m;i++){


            memo[0][i]=1;

        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                memo[i][j]=memo[i-1][j]+memo[i][j-1];


            }
        }
        return memo[r][c];






    }




}


