class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);

            int freeCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factorI == 0) {
                    freeCount++;
                }
            }

            // First copy gives: 1 (itself) + freeCount
            int firstCopyGain = 1 + freeCount;

            // 0/1 knapsack step: buy the 1st copy of item i (backwards to avoid double-counting free items)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
        }

        // Unbounded step: any remaining budget is best spent buying the cheapest item repeatedly (+1 copy per purchase)
        for (int b = minPrice; b <= budget; b++) {
            dp[b] = Math.max(dp[b], dp[b - minPrice] + 1);
        }

        int maxCopies = 0;
        for (int b = 0; b <= budget; b++) {
            maxCopies = Math.max(maxCopies, dp[b]);
        }
        return maxCopies;
    }
}
