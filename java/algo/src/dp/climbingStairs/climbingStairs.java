package dp.climbingStairs;
import java.util.Arrays;

// Top-down
class Solution1 {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(memo, n);
    }

    public int dp(int[] memo, int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (memo[n] == -1) {
            memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
        }
        return memo[n];
    }
}

// Bottom-up
class Solution2 {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

public class climbingStairs {
    public static void main(String[] args) {
        int n = 5; // Example input

        Solution1 solution1 = new Solution1();
        System.out.println("Solution 1: " + solution1.climbStairs(n));

        Solution2 solution2 = new Solution2();
        System.out.println("Solution 2: " + solution2.climbStairs(n));
    }
}
