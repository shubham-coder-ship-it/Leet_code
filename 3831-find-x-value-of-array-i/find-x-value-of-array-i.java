class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentMod = num % k;

            // Transition from previous subarrays
            for (int p = 0; p < k; p++) {
                if (dp[p] > 0) {
                    int nextMod = (p * currentMod) % k;
                    nextDp[nextMod] += dp[p];
                }
            }

            // Start a new subarray at the current element
            nextDp[currentMod]++;

            // Accumulate the counts into the final result
            for (int p = 0; p < k; p++) {
                result[p] += nextDp[p];
            }

            // Move to the next element
            dp = nextDp;
        }

        return result;
    }
}
