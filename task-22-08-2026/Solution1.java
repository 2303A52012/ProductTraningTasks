
public class Solution1 {
    // 740. Delete and Earn

    int[] dp;
    int[] points;

    public int deleteAndEarn(int[] nums) {

        points = new int[10001];
        for (int num : nums) points[num] += num;
        dp = new int[10001];
        for (int i = 0; i < dp.length; i++) dp[i] = -1;

        return solve(10000);
    }

    int solve(int i) {
        if (i <= 0) return 0;
        if (dp[i] != -1) return dp[i];
        return dp[i] = Math.max(solve(i - 1), points[i] + solve(i - 2));
    }

}