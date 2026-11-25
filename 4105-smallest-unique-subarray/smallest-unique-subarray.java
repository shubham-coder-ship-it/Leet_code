class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1, right = n;
        int ans = n;

        long base1 = 1315423911L, mod1 = 1000000007L;
        long base2 = 2654435761L, mod2 = 1000000009L;

        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] pref1 = new long[n + 1];
        long[] pref2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * base1) % mod1;
            pow2[i + 1] = (pow2[i] * base2) % mod2;
            pref1[i + 1] = (pref1[i] * base1 + nums[i]) % mod1;
            pref2[i + 1] = (pref2[i] * base2 + nums[i]) % mod2;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUnique(mid, n, pow1, pref1, mod1, pow2, pref2, mod2)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int len, int n, long[] pow1, long[] pref1, long mod1,
                              long[] pow2, long[] pref2, long mod2) {
        int count = n - len + 1;
        long[] hashes = new long[count];

        for (int i = 0; i < count; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % mod1 + mod1) % mod1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % mod2 + mod2) % mod2;
            hashes[i] = (h1 << 32) | h2;
        }

        java.util.Arrays.sort(hashes);

        for (int i = 0; i < count; i++) {
            boolean sameAsPrev = (i > 0 && hashes[i] == hashes[i - 1]);
            boolean sameAsNext = (i < count - 1 && hashes[i] == hashes[i + 1]);
            if (!sameAsPrev && !sameAsNext) {
                return true;
            }
        }

        return false;
    }
}
