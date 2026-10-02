import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String currentStr, int openCount, int closeCount, int max) {
        if (currentStr.length() == max * 2) {
            result.add(currentStr);
            return;
        }

        if (openCount < max) {
            backtrack(result, currentStr + "(", openCount + 1, closeCount, max);
        }
        if (closeCount < openCount) {
            backtrack(result, currentStr + ")", openCount, closeCount + 1, max);
        }
    }
}
