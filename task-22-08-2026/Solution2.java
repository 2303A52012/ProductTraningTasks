class Solution2 {
    // 416. partition equal subset sum
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i:nums) sum+=i;

        if(sum%2!=0) return false;

        int trgt = sum/2;

        boolean dp[] = new boolean[trgt+1];
        dp[0]= true;

        for(int i:nums){
            for(int j=trgt;j>=i;j--){
                dp[j] |= dp[j-i];
                if(dp[trgt]) return true;
            }
        } 
        return dp[trgt];
    }
}