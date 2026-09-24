class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int temp = nums[i];
            
            // Calculate sum of digits of nums[i]
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            
            // If nums[i] is 0, sum of digits is 0
            if (nums[i] == 0) {
                sum = 0;
            }
            
            if (sum == i) {
                return i;
            }
        }
        return -1;
    }
}
