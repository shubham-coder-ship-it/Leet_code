class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
     
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    
    for (String i : word1) sb1.append(i);
    for (String j : word2) sb2.append(j);
    
    return sb1.toString().equals(sb2.toString());

   
    }
}