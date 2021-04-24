package io.gen.desigin.pattern.decorator;

import java.util.HashMap;
import java.util.Map;

public class Dp {

    private Map<Integer, Integer> map = new HashMap<>();
    // f(n) = f(n - 1) + f(n - 2)
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int a = 1,b = 2,c = 0;
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // dp[i + 1] = max(dp[i - 1] + num[i], dp[i])  the i'st max value
    // dp[i] = max(dp[i - 2] + num[i], dp[i - 1])
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
        }
        return dp[nums.length];
    }

    private int rob(int[] nums, int i){
        if (map.containsKey(i)){
            return map.get(i);
        }
        if (i < 0){
            return 0;
        }
        int res = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
        map.put(i, res);
        return res;
    }


//  dp[i][j] = min(dp[i- 1][j], dp[i][j - 1])
    public int minPathSum(int[][] grid) {

        int[][] dp = new int[grid.length][];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (dp[i][j - 1] < dp[i - 1][j]){
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }else {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }


//  dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    //dp[i] = dp[i - 1] + 1 // the num of end of nums[i]
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
        }
        int sum = 0;
        for (int num: dp){
            sum += num;
        }
        return sum;
    }
}
