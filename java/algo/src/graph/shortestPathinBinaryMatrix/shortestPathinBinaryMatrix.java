package graph.shortestPathinBinaryMatrix;

import java.util.Queue;
import java.util.LinkedList;

import java.util.LinkedList;
import java.util.Queue;

class Solution1 {
    boolean isInRange(int r, int c, int rowLength, int colLength) {
        return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int shortestDist = -1;
        int rowLength = grid.length;
        int colLength = grid[0].length;

        if (grid[0][0] != 0 || grid[rowLength - 1][colLength - 1] != 0) {
            return shortestDist;
        }

        boolean visited[][] = new boolean[rowLength][colLength];

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            int curLength = curPos[2];

            if (curRow == rowLength - 1 && curCol == colLength - 1) {
                shortestDist = curLength;
                return shortestDist;
            }
            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];

                if (isInRange(nextRow, nextCol, rowLength, colLength)) {
                    if (grid[nextRow][nextCol] == 0 && !(visited[nextRow][nextCol])) {
                        queue.offer(new int[]{nextRow, nextCol, curLength + 1});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
        return shortestDist;
    }
}

class Solution2 {
    boolean isInRange(int r, int c, int rowLength, int colLength) {
        return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int shortestPathLength = -1;
        int rowLength = grid.length;
        int colLength = grid[0].length;

        if (grid[0][0] != 0 || grid[rowLength - 1][colLength - 1] != 0) {
            return shortestPathLength;
        }

        boolean visited[][] = new boolean[rowLength][colLength];

        int dr[] = {0, 1, 0, -1, 1, -1, -1, 1};
        int dc[] = {1, 0, -1, 0, 1, -1, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            int curLength = curPos[2];

            if (curRow == rowLength - 1 && curCol == colLength - 1) {
                shortestPathLength = curLength;
                return shortestPathLength;
            }
            for (int i = 0; i < 8; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (isInRange(nextRow, nextCol, rowLength, colLength)) {
                    if (grid[nextRow][nextCol] == 0 && !(visited[nextRow][nextCol])) {
                        queue.offer(new int[]{nextRow, nextCol, curLength + 1});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
        return shortestPathLength;
    }
}

public class shortestPathinBinaryMatrix  {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0}
        };

        Solution1 sol1 = new Solution1();
        int shortestPath1 = sol1.shortestPathBinaryMatrix(grid);
        System.out.println("Shortest Path Length (Solution 1): " + shortestPath1);

        Solution2 sol2 = new Solution2();
        int shortestPath2 = sol2.shortestPathBinaryMatrix(grid);
        System.out.println("Shortest Path Length (Solution 2): " + shortestPath2);
    }
}




