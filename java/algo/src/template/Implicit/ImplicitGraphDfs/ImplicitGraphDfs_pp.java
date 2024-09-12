package template.Implicit.ImplicitGraphDfs;



import java.util.*;

public class ImplicitGraphDfs_pp {
    static int[][] grid;
    static Map<String,Boolean> visited=new HashMap<>();
    static int row;
    static int col;


    public static void main(String[] args) {
        int cnt = 0;
        grid = new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        row = grid.length;

        col = grid[0].length;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visited.put(i + "," + j, false);
            }
        }








        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[i][j] == 1 && visited.get(i+","+j)== false) {
                    cnt++;
                    dfs(0, 0);

                }
            }

        }
        System.out.println(cnt);


    }

    public static void dfs(int cur_r, int cur_c) {
        visited.put(cur_r+","+cur_c,true);
        List<List<Integer>> d = new ArrayList<>();
        d.add(Arrays.asList(-1, 0));
        d.add(Arrays.asList(1, 0));
        d.add(Arrays.asList(0, -1));
        d.add(Arrays.asList(0, 1));
        // System.out.println(d);
        for (List<Integer> nexts : d) {

            int next_r = cur_r + nexts.get(0);
            int next_c = cur_c + nexts.get(1);
                //visited.get(next_r+","+next_c)== false
            if (0 <= next_r && next_r < row && 0 <= next_c && next_c < col) {
                if (visited.get(next_r+","+next_c)== false&& grid[next_r][next_c] == 1) {


                    dfs(next_r, next_c);
                }
            }
        }

        }


    }
