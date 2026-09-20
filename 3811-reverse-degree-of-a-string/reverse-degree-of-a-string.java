class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Calculate alphabet value ('a' = 26, 'b' = 25, ..., 'z' = 1)
            int alphabetValue = 26 - (c - 'a');
            // Calculate 1-indexed string position
            int stringPosition = i + 1;
            
            totalSum += alphabetValue * stringPosition;
        }
        
        return totalSum;
    }
}
