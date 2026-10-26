class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        int leftSize = (m + n + 1) / 2;

        while (low <= high) {
            int i = (low + high) / 2; 
            int j = leftSize - i;     

            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    return ((double) Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                high = i - 1; 
            } else {
                low = i + 1;  
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}
