class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] arr=new int[2];
        arr[0]=arr[1]=-1;
        int low=0;
        int high=nums.length-1;
        int[] ans=new int[]{-1,-1};
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target){
                ans[0]=mid;
                high=mid-1;
            }
            else if(nums[mid]<target){
                low=mid+1;
            }
            else{
                high=mid-1;
            }
        }
        low=0;
        high=nums.length-1;
         while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target){
                ans[1]=mid;
                low=mid+1;
            }
            else if(nums[mid]<target){
                low=mid+1;
            }
            else{
                high=mid-1;
            }
    }
    return ans;
    }
}