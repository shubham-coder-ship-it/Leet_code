class Solution {
    public int lengthOfLastWord(String s) {
        String trimmed = s.trim();
        return trimmed.length() - trimmed.lastIndexOf(' ') - 1;
    }
}
