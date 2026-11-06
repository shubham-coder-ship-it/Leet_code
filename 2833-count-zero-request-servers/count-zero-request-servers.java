class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] result = new int[m];
        
        // 1. Store queries with their original indices to preserve the correct order in the output
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // 2. Sort queries by their time values
        java.util.Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Sort logs by their timestamps
        java.util.Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 4. Sliding window setup
        // Track the count of active requests for each server using an array (since 1 <= server_id <= n)
        int[] serverCount = new int[n + 1]; 
        int uniqueServersWithRequests = 0;
        
        int left = 0;  // Out-of-window pointer (removes logs outside [query_time - x, query_time])
        int right = 0; // In-window pointer (adds logs up to query_time)
        
        // 5. Process each query using the sliding window
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIdx = sortedQueries[i][1];
            
            // Expand window: Add logs that fall within the range up to queryTime
            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    uniqueServersWithRequests++;
                }
                serverCount[serverId]++;
                right++;
            }
            
            // Shrink window: Remove logs that are strictly older than (queryTime - x)
            while (left < logs.length && logs[left][1] < queryTime - x) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    uniqueServersWithRequests--;
                }
                left++;
            }
            
            // Total servers minus the number of servers that received at least one request
            result[originalIdx] = n - uniqueServersWithRequests;
        }
        
        return result;
    }
}
