class Solution {
    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        
        // Sum up the differences between each element and the minimum
        return Arrays.stream(nums).map(num -> num - min).sum();
    }
}