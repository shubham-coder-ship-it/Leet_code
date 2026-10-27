class Solution {
    public String[] findWords(String[] words) {
      List<String> list= new ArrayList<>();
      String row1="qwertyuiop";
      String row2="asdfghjkl";
      String row3="zxcvbnm";
      for(String word:words){
        int[] temp=new int[3];
        for(char ch:word.toLowerCase().toCharArray()){
            if(row1.indexOf(ch)!=-1){
                temp[0]=1;
            }else if(row2.indexOf(ch)!=-1){
                temp[1]=1;
            }else{
                temp[2]=1;
            }
        }
        int sum=temp[0]+temp[1]+temp[2];
        if(sum==1){
            list.add(word);
        }
       
      }
       String[] ans= new String[list.size()];
        for(int i=0; i<list.size();i++){
            ans[i]=list.get(i);
        }
        return ans;
    }
}