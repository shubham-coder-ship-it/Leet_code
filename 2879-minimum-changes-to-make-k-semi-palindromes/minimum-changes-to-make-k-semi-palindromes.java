class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(s, i, j);
            }
        }
        
        int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;
        
        for (int part = 1; part <= k; part++) {
            for (int end = part * 2; end <= n; end++) {
                for (int prev = (part - 1) * 2; prev <= end - 2; prev++) {
                    if (dp[part - 1][prev] != INF) {
                        int currCost = dp[part - 1][prev] + cost[prev][end - 1];
                        if (currCost < dp[part][end]) {
                            dp[part][end] = currCost;
                        }
                    }
                }
            }
        }
        
        return dp[k][n];
    }
    
    private int getMinChanges(String s, int start, int end) {
        int len = end - start + 1;
        int minOps = len;
        
        for (int d = 1; d < len; d++) {
            if (len % d != 0) continue;
            
            int ops = 0;
            for (int rem = 0; rem < d; rem++) {
                int left = start + rem;
                int right = start + len - d + rem;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        ops++;
                    }
                    left += d;
                    right -= d;
                }
            }
            if (ops < minOps) {
                minOps = ops;
            }
        }
        
        return minOps;
    }
}
