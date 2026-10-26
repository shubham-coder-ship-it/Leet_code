class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        // Traverse every cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited piece of land
                if (grid[i][j] > 0) {
                    // Start DFS to compute the total value of this entire island
                    long islandSum = dfs(grid, i, j);
                    
                    // Check if the total island value is divisible by k
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private long dfs(int[][] grid, int r, int c) {
        // Base case: check boundary conditions and if the cell is water/visited (0)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        
        // Add the current cell's value to the island sum
        long currentSum = grid[r][c];
        
        // Mark the current cell as visited by setting it to 0
        grid[r][c] = 0;
        
        // Recursively explore all 4 directions
        currentSum += dfs(grid, r + 1, c); // Down
        currentSum += dfs(grid, r - 1, c); // Up
        currentSum += dfs(grid, r, c + 1); // Right
        currentSum += dfs(grid, r, c - 1); // Left
        
        return currentSum;
    }
}
