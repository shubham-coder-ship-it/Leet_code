class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            
            if (c == '(') {
                // Increment depth for an opening parenthesis
                depth++;
                // Assign to 0 (A) if depth is even, 1 (B) if depth is odd
                answer[i] = depth % 2;
            } else {
                // Assign to 0 (A) if depth is even, 1 (B) if depth is odd
                answer[i] = depth % 2;
                // Decrement depth for a closing parenthesis
                depth--;
            }
        }

        return answer;
    }
}
