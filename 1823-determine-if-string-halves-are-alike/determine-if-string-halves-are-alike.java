class Solution {
    public boolean halvesAreAlike(String s) {
       int n=s.length();
        int mid=n/2;
        String vowel="aeiouAEIOU";
        int countL=0;
        int countR=0;
        for (int i = 0; i < mid; i++) {
            if (vowel.indexOf(s.charAt(i)) != -1) {
                countL++;
            }
        }
       for (int i = mid; i < n; i++) {
            if (vowel.indexOf(s.charAt(i)) != -1) {
                countR++;
            }
        }
        return countL == countR;
    }
}