class Solution {
    public int missingNumber(int[] nums) {
        int sum=0;
        for(int val:nums){
            sum=sum+val;
    }
    int n=nums.length;
    int totalSum= (n*(n+1))/2;
    return totalSum-sum;
}}