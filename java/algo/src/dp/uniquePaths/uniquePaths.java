package dp.uniquePaths;

import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        for(int i = 0; i < memo.length; i++){
            Arrays.fill(memo[i], -1);
        }
        return dp(memo, m - 1, n - 1);
    }

    public int dp(int[][] memo, int r, int c){
        int uniquePath = 0;
        if (r == 0 && c ==0) {
            memo[r][c] = 1;
            return memo[r][c];
        }
        if (memo[r][c] == -1) {
            if((r - 1) >= 0){
                uniquePath += dp(memo, r - 1, c);
            }
            if((c - 1) >= 0){
                uniquePath += dp(memo, r, c - 1);
            }
            memo[r][c] = uniquePath;
        }
        return memo[r][c];
    }
}



class Solution2 {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            memo[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            memo[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }
}

public class uniquePaths {
    public static void main(String[] args) {
        // Test cases
        int m1 = 3, n1 = 7; // Example 1
        int m2 = 3, n2 = 2; // Example 2

        // Solution 1: Top-down
        Solution solution1 = new Solution();
        System.out.println("Solution 1 (Top-down) - Unique Paths for m = " + m1 + ", n = " + n1 + ": " + solution1.uniquePaths(m1, n1));
        System.out.println("Solution 1 (Top-down) - Unique Paths for m = " + m2 + ", n = " + n2 + ": " + solution1.uniquePaths(m2, n2));

        // Solution 2: Bottom-up
        Solution2 solution2 = new Solution2();
        System.out.println("Solution 2 (Bottom-up) - Unique Paths for m = " + m1 + ", n = " + n1 + ": " + solution2.uniquePaths(m1, n1));
        System.out.println("Solution 2 (Bottom-up) - Unique Paths for m = " + m2 + ", n = " + n2 + ": " + solution2.uniquePaths(m2, n2));
    }
}
