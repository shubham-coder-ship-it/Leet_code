class Solution {
    public List<Integer> getRow(int rowIndex) {
       List<Integer> list = new ArrayList<>();
        if(rowIndex<0)return list;
        long ans = 1;
        list.add(1);
    
        for (int i = 1; i <= rowIndex; i++) {
            ans = ans * (rowIndex - i+1);
            ans /= i;
            list.add((int) ans);
        }
        return list;
    
    }
}