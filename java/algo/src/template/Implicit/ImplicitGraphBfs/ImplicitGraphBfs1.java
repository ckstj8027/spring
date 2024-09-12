package template.Implicit.ImplicitGraphBfs;


import java.util.*;
public class ImplicitGraphBfs1 {
    static int[][] grid;
    static int row;
    static int col;
    static boolean[][] visited;

    static int cnt;
    static List<List<Integer>> d;



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

        d=new ArrayList<>();
        d.add(Arrays.asList(-1,0));
        d.add(Arrays.asList(1,0));
        d.add(Arrays.asList(0,-1));
        d.add(Arrays.asList(0,1));



        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {

                if (grid[i][j] == 1 && visited[i][j] == false) {



                    bfs(i, j);
                    cnt++;


                }
            }
        }
        System.out.println(cnt);
        System.out.println(visited[0][0]);




    }

    public static void bfs(int cur_row,int cur_col){



        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{cur_row,cur_col});
        visited[cur_row][cur_col]=true;



        while(!q.isEmpty()){

            int[] next_v=q.poll();


            for(List<Integer>  nexts:d){
                int next_r=nexts.get(0)+next_v[0];
                int next_c=nexts.get(1)+next_v[1];


                if(0<=next_r&&next_r<row &&0<=next_c&&next_c<col){
                    if(grid[next_r][next_c]==1 && visited[next_r][next_c]==false){

                        System.out.println(next_r+","+next_c);
                        q.offer(new int[]   {next_r,next_c});
                        visited[next_r][next_c]=true;


                    }
                }




            }

        }

    }


}