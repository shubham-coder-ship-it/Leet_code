import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        // multCount[f] = count of items whose factor is a multiple of f
        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
        }

        // Collect all 2-for-1 bundles strictly better than buying two minPrice items
        long[][] bundles = new long[n][2];
        int bCount = 0;
        for (int[] item : items) {
            int f = item[0];
            long p = item[1];
            int k = multCount[f] - 1;
            if (k > 0 && p < 2 * minPrice) {
                bundles[bCount][0] = p;
                bundles[bCount][1] = k;
                bCount++;
            }
        }

        Arrays.sort(bundles, 0, bCount, (a, b) -> Long.compare(a[0], b[0]));

        long rem = budget;
        long totalItems = 0;

        for (int i = 0; i < bCount; i++) {
            long p = bundles[i][0];
            long k = bundles[i][1];
            long take = Math.min(k, rem / p);
            if (take == 0) break;
            totalItems += take * 2;
            rem -= take * p;
        }

        totalItems += rem / minPrice;
        return (int) totalItems;
    }
}
