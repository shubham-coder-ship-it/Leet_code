class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long result = 0;
        int prefixSum = 0;
        
        // An empty prefix has a sum of 0, which is even
        int evenCount = 1; 
        int oddCount = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                // Even prefix sum minus any previous odd prefix sum yields an odd subarray sum
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                // Odd prefix sum minus any previous even prefix sum yields an odd subarray sum
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }

        return (int) result;
    }
}
