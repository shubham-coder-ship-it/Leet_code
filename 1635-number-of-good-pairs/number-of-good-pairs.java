class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        int[] frequency = new int[101]; 
        
        for (int num : nums) {
            count += frequency[num];
            frequency[num]++;
        }
        
        return count;
    }
}
