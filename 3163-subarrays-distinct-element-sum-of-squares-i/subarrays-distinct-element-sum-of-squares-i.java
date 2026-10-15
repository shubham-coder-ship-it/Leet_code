import java.util.List;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSumOfSquares = 0;

        // Iterate through all possible starting points of subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            
            // Expand the subarray from index i to j
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                
                // Square the size of the distinct set and add to total
                int count = distinctElements.size();
                totalSumOfSquares += count * count;
            }
        }

        return totalSumOfSquares;
    }
}
