class Solution {
    public int removeDuplicates(int[] nums) {
        
        int k = 0;
        for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[k]) {
        k++;
                nums[k] = nums[j];
            }
        }
        return k+1 ;
    }
}
