import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute Pascal's triangle for combinations nCr % MOD
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original array configuration itself
        return (int) ((getWays(list) - 1 + MOD) % MOD);
    }

    private long getWays(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition remaining elements into left and right subtrees
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = getWays(left);
        long rightWays = getWays(right);
        
        // Interleave left and right subtree sequences
        long interleaveWays = table[left.size() + right.size()][left.size()];

        // Total ways for the current node
        return (((interleaveWays * leftWays) % MOD) * rightWays) % MOD;
    }
}
