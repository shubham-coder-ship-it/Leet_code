class Solution {
    public boolean canAliceWin(int[] nums) {
        int single=0;
        int doubl=0;
        for(int val:nums){
            if(val<10){
                single+=val;
            }
            else{
                doubl+=val;
            }
        }
        return (single>doubl)||(doubl>single);
    }
}