package template.Implicit.ImplicitGraphDfs;

import java.awt.datatransfer.FlavorEvent;

class ImplicitGraphDfs1 {
    static boolean[][] visited;
    static int[][] grid;
    static int count=0;

    public static void main(String[] args) {
        int numberOfIslands = 0;
        grid = new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int rowLength = grid.length;
        int colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];


        for(int i=0;i<rowLength ;i++){
            for(int j=0;j<colLength ;j++){
                if(grid[i][j]==1 &&visited[i][j]== false){
                    dfs(0, 0);
                    count++;

                }
            }


        }
        System.out.println(count);



    }
    public static void dfs(int r, int c){
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        visited[r][c] = true;

        for(int i = 0; i < 4; i++){
            int nextRow = r + dr[i];
            int nextCol = c + dc[i];
            if(rowLength > nextRow && nextRow >= 0 &&  colLength > nextCol && nextCol >= 0){
                if(grid[nextRow][nextCol] == 1){
                    if(!visited[nextRow][nextCol]) {
                        //System.out.println(nextRow+" , "  + nextCol);
                        dfs(nextRow, nextCol);
                    }
                }
            }
        }
    }
}