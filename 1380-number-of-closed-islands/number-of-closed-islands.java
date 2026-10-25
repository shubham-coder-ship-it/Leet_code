class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Flood fill all land (0) connected to the grid boundaries
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) dfs(grid, r, 0);
            if (grid[r][cols - 1] == 0) dfs(grid, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) dfs(grid, 0, c);
            if (grid[rows - 1][c] == 0) dfs(grid, rows - 1, c);
        }
        
        // Step 2: Count the remaining isolated islands
        int closedIslandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c); // Sink the counted island
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        // Base case: Out of bounds or cell is water (1)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark the land cell as water to avoid cycles / recounting
        grid[r][c] = 1;
        
        // Explore 4-directionally
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
