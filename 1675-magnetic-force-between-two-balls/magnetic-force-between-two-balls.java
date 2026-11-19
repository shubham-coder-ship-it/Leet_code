class Solution {
    public int maxDistance(int[] position, int m) {
       Arrays.sort(position) ;
       int ans=0;
       int low=1;
       int high=position[position.length-1]-position[0];
       while(low<=high){
        int mid=low+(high-low)/2;
        if(isPossible(position,m,mid)){
            ans=mid;
            low=mid+1;

        }else{
            high=mid-1;
        }
       }
       return ans;
    }
    public boolean isPossible(int[] nums, int m , int mid){
        int count=1;
        int pos=nums[0];
        for(int i=1;i<nums.length; i++){
            if(nums[i]-pos>=mid){
                count++;
                pos=nums[i];
            }
        }
        return count>=m;
    }
}