class Solution {
    public void moveZeroes(int[] nums) {
     int n=nums.length;
     int count =0;
     for(int  val: nums){
        if(val!=0){
            nums[count++]=val;
        }
     }   
     while(count<n){
        nums[count++]=0;
     }
    }
}