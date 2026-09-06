class Solution3 {

    // 494. target sum

    public int findTargetSumWays(int[] nums, int target) {

        int totalSum = 0;
        for (int x : nums) totalSum += x;
        if (Math.abs(target) > Math.abs(totalSum)) return 0;
        if ((target + totalSum) % 2 != 0) return 0;
        int sum = (target + totalSum) / 2;
        int[][] dp = new int[nums.length][sum + 1];
        for(int i[]:dp) Arrays.fill(i,-1);
        return solve(nums.length - 1, sum, nums, dp);
    }

    private int solve(int idx, int sum, int[] nums, int[][] dp) {

        if (idx == 0) {
            if (sum == 0 && nums[0] == 0) return 2;
            if(sum==0) return 1;
            if (sum == nums[0]) return 1;
            return 0;
        }

        if (dp[idx][sum] != -1) return dp[idx][sum];

        int notPick = solve(idx - 1, sum, nums, dp);    
        int pick = 0;

        if (nums[idx] <= sum)
            pick = solve(idx - 1, sum - nums[idx], nums, dp);

        return dp[idx][sum] = pick + notPick;
    }
}