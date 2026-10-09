public class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int left = 0;
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            if (s.charAt(i) == '(') {
                left++;
                i++;
            } else {
                // Check if there is a consecutive ')' to form '))'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    res++; // Missing one ')' to make a pair
                    i++;
                }
                
                // Match the closing pair with an open '('
                if (left > 0) {
                    left--;
                } else {
                    res++; // Missing an opening '('
                }
            }
        }
        
        // Each remaining open '(' needs two ')'
        res += left * 2;
        return res;
    }
}
