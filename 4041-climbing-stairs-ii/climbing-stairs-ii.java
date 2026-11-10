class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
        // Base case: starting at step 0 costs nothing
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            // Option 1: Jump 1 step from i - 1
            int jump1 = dp[i - 1] + costs[i - 1] + 1;
            
            // Option 2: Jump 2 steps from i - 2 (if valid)
            int jump2 = (i >= 2) ? dp[i - 2] + costs[i - 1] + 4 : Integer.MAX_VALUE;
            
            // Option 3: Jump 3 steps from i - 3 (if valid)
            int jump3 = (i >= 3) ? dp[i - 3] + costs[i - 1] + 9 : Integer.MAX_VALUE;
            
            // Take the minimum of all valid options
            dp[i] = Math.min(jump1, Math.min(jump2, jump3));
        }
        
        return dp[n];
    }
}
