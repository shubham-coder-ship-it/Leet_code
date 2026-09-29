class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int val:nums){
            set.add(val);
        }    
        List<Integer> list= new ArrayList<>();
        for(int i=1;i<=nums.length; i++){
            if(!set.contains(i)){
                list.add(i);
            }
        }
    
        return list;
    }
}