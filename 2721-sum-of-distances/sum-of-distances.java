import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];
        
        // Step 1: Map each unique value to a list of its indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Step 2: Compute distances for each group of identical elements
        for (List<Integer> indices : map.values()) {
            int len = indices.size();
            if (len == 1) continue; // Distances default to 0
            
            // Calculate total sum of indices for suffix calculations
            long totalSum = 0;
            for (int idx : indices) {
                totalSum += idx;
            }
            
            long leftSum = 0;
            for (int j = 0; j < len; j++) {
                long currIdx = indices.get(j);
                long rightSum = totalSum - leftSum - currIdx;
                
                // Elements to the left
                long leftCount = j;
                long leftTotal = (leftCount * currIdx) - leftSum;
                
                // Elements to the right
                long rightCount = len - 1 - j;
                long rightTotal = rightSum - (rightCount * currIdx);
                
                // Assign total absolute difference to result array
                result[(int) currIdx] = leftTotal + rightTotal;
                
                // Update prefix sum for the next iteration
                leftSum += currIdx;
            }
        }
        
        return result;
    }
}
