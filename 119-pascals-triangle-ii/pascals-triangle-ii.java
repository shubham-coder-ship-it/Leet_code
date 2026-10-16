class Solution {
    public List<Integer> getRow(int rowIndex) {
        java.util.List<Integer> row = new java.util.ArrayList<>();
        long ans = 1;
        row.add((int) ans);
        for (int i = 0; i < rowIndex; i++) {
            ans = ans * (rowIndex - i);
            ans = ans / (i + 1);
            row.add((int) ans);
        }
        return row;
    
    }
}