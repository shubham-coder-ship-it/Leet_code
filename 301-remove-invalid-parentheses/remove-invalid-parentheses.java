import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        
        // Queue for BFS and Set to track visited states to prevent duplicates
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(s);
        visited.add(s);
        
        boolean foundValidAtThisLevel = false;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process the current level fully
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                
                // If the string is valid, add it to our results
                if (isValid(current)) {
                    result.add(current);
                    foundValidAtThisLevel = true; 
                }
                
                // If we already found a valid string at this level, 
                // we don't generate deeper states (ensures minimum removals)
                if (foundValidAtThisLevel) continue;
                
                // Generate next states by removing one parenthesis at a time
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    
                    // Only remove parentheses, ignore letters
                    if (c == '(' || c == ')') {
                        String nextState = current.substring(0, j) + current.substring(j + 1);
                        
                        if (!visited.contains(nextState)) {
                            visited.add(nextState);
                            queue.add(nextState);
                        }
                    }
                }
            }
            
            // Stop BFS if we found the minimum removal answers at this level
            if (foundValidAtThisLevel) break;
        }
        
        return result;
    }
    
    // Helper function to check if a string has balanced parentheses
    private boolean isValid(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing than opening
            }
        }
        return count == 0;
    }
}
