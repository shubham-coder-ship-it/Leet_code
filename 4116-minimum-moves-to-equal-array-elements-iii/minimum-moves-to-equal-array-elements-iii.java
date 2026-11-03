class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        int i = 1;
        while (i < nums.length) {
            if (nums[i] > max) {
                max = nums[i];
            }
            i++;
        }
        int moves = 0;
        i = 0;
        while (i < nums.length) {
            moves += max - nums[i];
            i++;
        }
        return moves;
    }
}
