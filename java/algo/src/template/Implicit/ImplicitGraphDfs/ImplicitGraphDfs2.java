package template.Implicit.ImplicitGraphDfs;

class ImplicitGraphDfs2 {
    static boolean[][] visited;
    static int[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int rowLength;
    static int colLength;

    public static boolean isValid(int r, int c) {
        return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength) && (grid[r][c] == 1);
    }

    public static void main(String[] args) {
        int numberOfIslands = 0;
        grid = new int[][]{{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
        rowLength = grid.length;
        colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];
        dfs(0, 0);
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = r + dr[i];
            int nextCol = c + dc[i];
            if (isValid(nextRow, nextCol)) {
                if (!visited[nextRow][nextCol]) {
                    dfs(nextRow, nextCol);
                }
            }
        }
    }
}