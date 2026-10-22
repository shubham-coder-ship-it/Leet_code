class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int idx = -1;
        
        // Find the index of element 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }
        
        // Case 1: Check if elements are increasing clockwise (to the right) from 0
        boolean incRight = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx + i) % n] != i) {
                incRight = false;
                break;
            }
        }
        
        if (incRight) {
            // Option A: Rotate directly to the left by `idx` positions
            // Option B: Reverse once, rotate left by `n - idx` positions, reverse back
            return Math.min(idx, n - idx + 2);
        }
        
        // Case 2: Check if elements are increasing counter-clockwise (to the left) from 0
        boolean incLeft = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx - i + n) % n] != i) {
                incLeft = false;
                break;
            }
        }
        
        if (incLeft) {
            // Option A: Rotate left to move 0 to the end, then reverse
            int op1 = ((idx + 1) % n) + 1;
            // Option B: Reverse first, then rotate left to move 0 to the beginning
            int op2 = n - idx;
            return Math.min(op1, op2);
        }
        
        // If neither cyclic order is satisfied, sorting is impossible
        return -1;
    }
}
