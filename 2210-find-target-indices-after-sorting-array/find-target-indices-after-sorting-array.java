import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        // 1. Sort the array in ascending order
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        
        // 2. Collect all indices where the value matches the target
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        return result;
    }
}
