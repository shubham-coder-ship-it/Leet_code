class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        
        for (int val : piles) {
            high = Math.max(high, val);
        }
        
        int ans = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (isPossible(piles, h, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public boolean isPossible(int[] nums, int h, int mid) {
        long count = 0;
        for (int val : nums) {
            count += (long) Math.ceil((double) val / (double) mid);
        }
        return count <= h;
    }
}
