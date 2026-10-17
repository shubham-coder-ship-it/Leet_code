class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // pair[i][0] = value, pair[i][1] = original number
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = getDigitSum(nums[i]);
            pairs[i][1] = nums[i];
        }

        // Sort based on: 1. Digit sum, 2. Numerical value
        java.util.Arrays.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Map each unique number to its target sorted position
        java.util.Map<Integer, Integer> targetPos = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            targetPos.put(pairs[i][1], i);
        }

        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        for (int i = 0; i < n; i++) {
            // Skip if already visited or already in the correct sorted spot
            if (visited[i] || nums[i] == pairs[i][1]) {
                continue;
            }

            // Trace the permutation cycle
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = targetPos.get(nums[j]); 
                cycleSize++;
            }

            // A cycle of size K takes K - 1 swaps to fix
            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }

        return totalSwaps;
    }

    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
