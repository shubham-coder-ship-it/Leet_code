class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
       int ans=0;
      int count=0;
      for(int i : nums){
        if(i==0){
            count =0;
    
        }
        else {
            count++;
            ans=Math.max(ans,count);
        }
      }
      return ans;

    }
}