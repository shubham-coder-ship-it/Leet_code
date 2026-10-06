import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < matrix.length; i++) {
            int minVal = matrix[i][0];
            int minColIdx = 0; // Declared here so the rest of the loop can see it

            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j];
                    minColIdx = j; 
                }
            }

            boolean isMaxInCol = true;
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][minColIdx] > minVal) {
                    isMaxInCol = false; 
                    break;
                }
            }
            
            if (isMaxInCol) {
                result.add(minVal);
            }
        }
        
        return result;
    }
}
