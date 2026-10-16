import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment Tree Nodes
    private long[] sum;
    private long[] sqSum;
    private long[] lazy;

    private void pushDown(int node, int l, int r) {
        if (lazy[node] == 0) {
            return;
        }

        long val = lazy[node];
        int mid = l + (r - l) / 2;

        // Left Child Update
        int leftLen = mid - l + 1;
        sqSum[2 * node] = (sqSum[2 * node] + 2 * val * sum[2 * node] + val * val % MOD * leftLen) % MOD;
        sum[2 * node] = (sum[2 * node] + val * leftLen) % MOD;
        lazy[2 * node] = (lazy[2 * node] + val) % MOD;

        // Right Child Update
        int rightLen = r - mid;
        sqSum[2 * node + 1] = (sqSum[2 * node + 1] + 2 * val * sum[2 * node + 1] + val * val % MOD * rightLen) % MOD;
        sum[2 * node + 1] = (sum[2 * node + 1] + val * rightLen) % MOD;
        lazy[2 * node + 1] = (lazy[2 * node + 1] + val) % MOD;

        lazy[node] = 0;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            int length = r - l + 1;
            sqSum[node] = (sqSum[node] + 2 * val * sum[node] + val * val % MOD * length) % MOD;
            sum[node] = (sum[node] + val * length) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }

        pushDown(node, l, r);
        int mid = l + (r - l) / 2;

        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }

        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        
        // Allocate space for the 1-indexed complete Segment Tree
        sum = new long[4 * n];
        sqSum = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastPos = new HashMap<>();
        long totalAns = 0;

        for (int i = 0; i < n; i++) {
            // Find the position right after the last occurrence of nums[i]
            int prevIdx = lastPos.getOrDefault(nums[i], -1);

            // Increment range [prevIdx + 1, i] by 1
            update(1, 0, n - 1, prevIdx + 1, i, 1);

            // The root node (index 1) holds the total squared sum for all subarrays ending at index i
            totalAns = (totalAns + sqSum[1]) % MOD;

            // Update the map tracker
            lastPos.put(nums[i], i);
        }

        return (int) totalAns;
    }
}
