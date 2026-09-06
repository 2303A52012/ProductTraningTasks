class Solution4 {
    // 983. Minimum Cost For Tickets

    int[] dp;

    public int mincostTickets(int[] days, int[] costs) {
        dp = new int[days.length];
        
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }

        return solve(0, days, costs);
    }

    int solve(int i, int[] days, int[] costs) {
        if (i >= days.length) return 0;

        if (dp[i] != -1) return dp[i];
        int oneDay = costs[0] + solve(i + 1, days, costs);
        int j = i;

        while (j < days.length && days[j] < days[i] + 7) j++;
        
        int sevenDay = costs[1] + solve(j, days, costs);
        j = i;
        while (j < days.length && days[j] < days[i] + 30) j++;
        int thirtyDay = costs[2] + solve(j, days, costs);

        dp[i] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
        return dp[i];
    }
}