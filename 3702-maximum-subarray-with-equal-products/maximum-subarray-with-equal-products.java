class Solution {
    public int maxLength(int[] nums) {
        int maxLen = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            long prod = 1;
            long g = 0;
            long l = 1;
            
            for (int j = i; j < n; j++) {
                prod *= nums[j];
                g = (g == 0) ? nums[j] : gcd(g, nums[j]);
                l = lcm(l, nums[j]);
                
                // Pruning or check if product exceeds safe upper bound
                if (prod > 1e18) break; // Avoid overflow if needed
                
                if (prod == l * g) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
