class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
      Map<Integer, List<Integer>>map=new HashMap<>();
      int total=0;
      int maxDiagonal=0;
      for(int i=0;i<nums.size();i++){
        for(int j=0; j<nums.get(i).size();j++){
            int diag=i+j;
            map.putIfAbsent(diag, new ArrayList<>());
            map.get(diag).add(nums.get(i).get(j));
            total++;
            maxDiagonal=Math.max(maxDiagonal,diag);
        }
      }  
      int [] ans=new int [total];
      int idx=0;
      for(int i=0; i<=maxDiagonal;i++){
        List<Integer> temp=map.get(i);
        if(temp==null)continue;
        for(int j=temp.size()-1;j>=0;j--){
            ans[idx]=temp.get(j);
            idx++;
        }
      }
      return ans;
    }
}