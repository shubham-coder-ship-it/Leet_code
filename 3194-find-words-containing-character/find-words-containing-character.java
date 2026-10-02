class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
     int n=words.length;
     
     List<Integer> ans= new ArrayList<>();
     //String s=new String(x);
     for (int i=0 ; i<n;i++){
        if(words[i].indexOf(x) != -1){
            ans.add(i);
        }
     }  
     return ans; 
    }
}