class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // ways[i][j] tracks total unique paths arriving or starting from cell (i, j)
        int[][] ways = new int[m][n];
        ways[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If no paths can reach this cell, skip it
                if (ways[i][j] == 0) continue;
                
                // If we reached the destination, don't move forward from it
                if (i == m - 1 && j == n - 1) continue;

                // --- 1. MOVE RIGHT ---
                int nextI = i;
                int nextJ = j + 1;
                int currentIntent = 0; // 0 = Right, 1 = Down

                // Simulate full deflection chain if mirror cells are hit
                while (nextI < m && nextJ < n && grid[nextI][nextJ] == 1) {
                    if (currentIntent == 0) { // Moving Right -> Deflects Down
                        nextI++;
                        currentIntent = 1;
                    } else {                  // Moving Down -> Deflects Right
                        nextJ++;
                        currentIntent = 0;
                    }
                }
                // If it lands inside the grid boundaries, accumulate paths
                if (nextI < m && nextJ < n) {
                    ways[nextI][nextJ] = (ways[nextI][nextJ] + ways[i][j]) % MOD;
                }

                // --- 2. MOVE DOWN ---
                nextI = i + 1;
                nextJ = j;
                currentIntent = 1; // 0 = Right, 1 = Down

                // Simulate full deflection chain if mirror cells are hit
                while (nextI < m && nextJ < n && grid[nextI][nextJ] == 1) {
                    if (currentIntent == 1) { // Moving Down -> Deflects Right
                        nextJ++;
                        currentIntent = 0;
                    } else {                  // Moving Right -> Deflects Down
                        nextI++;
                        currentIntent = 1;
                    }
                }
                // If it lands inside the grid boundaries, accumulate paths
                if (nextI < m && nextJ < n) {
                    ways[nextI][nextJ] = (ways[nextI][nextJ] + ways[i][j]) % MOD;
                }
            }
        }

        return ways[m - 1][n - 1];
    }
}
