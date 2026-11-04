class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            packed[i] = ((long) nums[i] << 32) | (i & 0xFFFFFFFFL);
        }
        java.util.Arrays.sort(packed);

        int[] countBit = new int[n + 1];
        long[] sumBit = new long[n + 1];
        long[] C = new long[n - x + 1];
        int targetRank = (x + 1) / 2;

        for (int i = 0; i < n; i++) {
            long currentPacked = ((long) nums[i] << 32) | (i & 0xFFFFFFFFL);
            int idx = java.util.Arrays.binarySearch(packed, currentPacked) + 1;

            for (int j = idx; j <= n; j += j & -j) {
                countBit[j]++;
                sumBit[j] += nums[i];
            }

            if (i >= x) {
                long oldPacked = ((long) nums[i - x] << 32) | ((i - x) & 0xFFFFFFFFL);
                int oldIdx = java.util.Arrays.binarySearch(packed, oldPacked) + 1;
                for (int j = oldIdx; j <= n; j += j & -j) {
                    countBit[j]--;
                    sumBit[j] -= nums[i - x];
                }
            }

            if (i >= x - 1) {
                int medianIdx = 0;
                int currentCount = 0;
                int step = 1;
                while (step <= n) {
                    step <<= 1;
                }
                step >>= 1;

                while (step > 0) {
                    int nextIdx = medianIdx + step;
                    if (nextIdx <= n && currentCount + countBit[nextIdx] < targetRank) {
                        medianIdx = nextIdx;
                        currentCount += countBit[medianIdx];
                    }
                    step >>= 1;
                }
                medianIdx++;
                
                long medianValue = (int) (packed[medianIdx - 1] >> 32);

                long leftSum = 0;
                for (int j = medianIdx; j > 0; j -= j & -j) {
                    leftSum += sumBit[j];
                }

                long totalSum = 0;
                for (int j = n; j > 0; j -= j & -j) {
                    totalSum += sumBit[j];
                }
                long rightSum = totalSum - leftSum;

                long leftOps = targetRank * medianValue - leftSum;
                long rightOps = rightSum - (long) (x - targetRank) * medianValue;
                C[i - x + 1] = leftOps + rightOps;
            }
        }

        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = 1000000000000000L;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (i >= x && dp[i - x][j - 1] != 1000000000000000L) {
                    long cost = dp[i - x][j - 1] + C[i - x];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[n][k];
    }
}
