package template.Implicit.ImplicitGraphBfs;
import java.util.*;
public class ImplicitGraphBfs {
    static int[][] grid;
    static int row;
    static int col;
    static int cnt;

    static boolean[][] visited;

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



        for(int i=0 ;i<row ; i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1&&visited[i][j]==false){
                    bfs(i,j);
                    cnt++;

                }

            }
        }
        System.out.println(cnt);

    }


    public static void bfs(int cur_r,int cur_c){
        Queue<Integer[]> q=new LinkedList<>();
       // System.out.println(cur_r+","+cur_c);
        q.offer(new Integer[]{cur_r,cur_c});
        List<List<Integer>>  d=new ArrayList<>();
        d.add(Arrays.asList(-1,0));
        d.add(Arrays.asList(1,0));
        d.add(Arrays.asList(0,-1));
        d.add(Arrays.asList(0,1));

        while(!q.isEmpty()){
            Integer[] cur=q.poll();
            //System.out.println(cur[0]);
            visited[cur[0]][cur[1]]=true;
            for (List<Integer> nexts:d){
                int next_r=nexts.get(0) +cur[0]    ;
                int next_c=nexts.get(1) +cur[1]    ;




                if(0<=next_r&&next_r<row  &&0<=next_c&&next_c<col){
                    if(visited[next_r][next_c]==false &&grid[next_r][next_c]==1){
                        System.out.println(next_r+","+next_c);
                        visited[next_r][next_c]=true;
                        q.offer(new Integer[]{next_r,next_c});

                    }
                }


                }

        }




    }


    }



