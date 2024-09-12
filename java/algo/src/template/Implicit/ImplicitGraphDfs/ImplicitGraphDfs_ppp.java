package template.Implicit.ImplicitGraphDfs;



import java.util.*;

public class ImplicitGraphDfs_ppp {
    static int[][] grid;
    static boolean[][]  visited;
    static int row;
    static int col;

    static int cnt;


    public static void main(String[] args) {
        int cnt = 0;
        grid = new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        row=grid.length;
        col=grid[0].length;
        visited=new boolean[row][col];






        for(int i=0;i<row ;i++){
            for(int j=0;j<col;j++){
                if(visited[i][j]==false&&grid[i][j]==1){
                    dfs(i,j);
                    cnt++;

                }
            }
        }
        System.out.println(cnt);


    }

    public static void dfs(int cur_r,int cur_c){
        System.out.println(cur_r+","+cur_c);

        visited[cur_r][cur_c] = true;

         List<List<Integer>>  d=new ArrayList<>();
         d.add(Arrays.asList(-1,0));
         d.add(Arrays.asList(1,0));
         d.add(Arrays.asList(0,-1));
         d.add(Arrays.asList(0,1));

         for(List<Integer> nexts:d){
             int next_r=cur_r+nexts.get(0);  //함수
             int next_c=cur_c+ nexts.get(1);  // 함수쓰기



             if(  0<=next_r &&next_r<row   &&    0<=next_c &&next_c<col){
                 if(visited[next_r][next_c]==false&&grid[next_r][next_c]==1){

                     dfs(next_r,next_c);
                 }
             }
         }



    }
}