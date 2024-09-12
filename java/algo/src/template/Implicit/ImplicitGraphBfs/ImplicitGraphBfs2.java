package template.Implicit.ImplicitGraphBfs;
import java.util.*;
public class ImplicitGraphBfs2 {

    static int[][] grid;
    static boolean[][] visited;
    static int row;
    static int col;
    static int[][]d;
    static int cnt;
    public static void main(String[] args) {
        grid = new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        row=grid.length;
        col=grid[0].length;

        visited=new boolean[row][col];
        d=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if(visited[i][j]==false&&grid[i][j]==1){
                    cnt++;
                    bfs(i,j);
                }
            }

        }
        System.out.println(cnt);

    }

    public static void bfs(int cur_r,int cur_c){
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{cur_r,cur_c});
        visited[cur_r][cur_c]=true;

        while (!q.isEmpty()){
            int[] next=q.poll();
            for(int[] nexts:d){

                int next_r=nexts[0]+next[0];
                int next_c=nexts[1]+next[1];


                if (0<=next_r && next_r<row&&0<=next_c && next_c<col){
                    if(grid[next_r][next_c]==1&& visited[next_r][next_c]==false){

                        q.offer(new int[]{next_r,next_c});
                        visited[next_r][next_c]=true;

                    }

                }
            }
        }


    }
}

