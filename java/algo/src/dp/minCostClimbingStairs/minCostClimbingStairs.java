package dp.minCostClimbingStairs;

import java.util.Arrays;

// Top-down
class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(memo, cost, n);
    }

    public int dp(int[] memo, int[] cost, int n) {
        if (n == 0 || n == 1) {
            return 0;
        } else if (memo[n] == -1) {
            memo[n] = Math.min(dp(memo, cost, n - 1) + cost[n - 1], dp(memo, cost, n - 2) + cost[n - 2]);
        }
        return memo[n];
    }
}

// Bottom-up
class Solution2 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 0;
        for (int i = 2; i < n + 1; ++i) {
            memo[i] = Math.min(memo[i - 1] + cost[i - 1], memo[i - 2] + cost[i - 2]);
        }
        return memo[n];
    }
}

public class minCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost1 = {10, 15, 20}; // Example cost array 1
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}; // Example cost array 2

        Solution1 solution1 = new Solution1();
        System.out.println("Solution 1 (Cost 1): " + solution1.minCostClimbingStairs(cost1));
        System.out.println("Solution 1 (Cost 2): " + solution1.minCostClimbingStairs(cost2));

        Solution2 solution2 = new Solution2();
        System.out.println("Solution 2 (Cost 1): " + solution2.minCostClimbingStairs(cost1));
        System.out.println("Solution 2 (Cost 2): " + solution2.minCostClimbingStairs(cost2));
    }
}
