class Solution5 {
    // 1049. Last Stone Weight II
    
    public int lastStoneWeightII(int[] stones) {

        int sum = 0;

        for (int i : stones) sum += i;

        int trgt = sum/2;
        int[] dp = new int[trgt + 1];
        for (int i : stones) 
            for (int j = trgt; j >= i; j--) 
                dp[j] = Math.max( dp[j], dp[j - i] + i );
        
        return sum - 2 * dp[trgt];
    }
}