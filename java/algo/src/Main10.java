

import java.util.Queue;
import java.util.LinkedList;

public class Main10 {
    public static void main(String[] args) {
        // Given grid
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        Solution_BFS solution_bfs = new Solution_BFS();
        int numberOfIslands_BFS1 = solution_bfs.numIslands(grid);
        System.out.println("Number of Islands (BFS): " + numberOfIslands_BFS1);

        Solution_DFS solution_dfs = new Solution_DFS();
        int numberOfIslands_DFS = solution_dfs.numIslands(grid);
        System.out.println("Number of Islands (DFS): " + numberOfIslands_DFS);
    }
}

class Solution_BFS {
    boolean isInRange(int r, int c, int rowLength, int colLength) {
        return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength);
    }

    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;
        boolean visited[][] = new boolean[rowLength][colLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if ((grid[i][j] == '1') && (!visited[i][j])) {
                    bfs(i, j, grid, visited);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }

    public void bfs(int r, int c, char[][] grid, boolean[][] visited) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        Pair<Integer,Integer> qwe=new Pair<>();
        qwe.set(r, c);
        q.offer(qwe);
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Pair<Integer, Integer> cur = q.poll();
            int curRow = cur.getLeft();
            int curCol = cur.getRight();

            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];

                if (isInRange(nextRow, nextCol, rowLength, colLength)) {
                    if (grid[nextRow][nextCol] == '1' && !(visited[nextRow][nextCol])) {
                        visited[nextRow][nextCol] = true;
                        Pair<Integer,Integer> qwer=new Pair<>();
                        qwer.set(nextRow, nextCol);
                        q.offer(qwer);
                    }
                }
            }
        }
    }

    class Pair<L, R> {
        L left;
        R right;

     /*   private Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }*/
        public void set(L left, R right){
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return this.left;
        }

        public R getRight() {
            return this.right;
        }
    }
}

class Solution_DFS {
    static boolean visited[][];

    boolean isInRange(int r, int c, int rowLength, int colLength) {
        return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength);
    }

    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if ((grid[i][j] == '1') && (!visited[i][j])) {
                    dfs(i, j, grid);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }

    public void dfs(int r, int c, char[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[r][c] = true;

        for (int[] d : dir) {
            int nextRow = r + d[0];
            int nextCol = c + d[1];
            if (isInRange(nextRow, nextCol, rowLength, colLength)) {
                if (grid[nextRow][nextCol] == '1' && !(visited[nextRow][nextCol])) {
                    dfs(nextRow, nextCol, grid);
                }
            }
        }
    }
}


