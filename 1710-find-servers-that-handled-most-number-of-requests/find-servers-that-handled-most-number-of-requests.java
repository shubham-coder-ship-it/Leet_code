class Solution {
    public java.util.List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests handled by each server
        int[] requestCount = new int[k];
        
        // PriorityQueue to track busy servers, ordered by their free time: [freeTime, serverId]
        java.util.PriorityQueue<int[]> busyServers = new java.util.PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // TreeSet to store currently available servers for quick O(log K) lookups and ceiling queries
        java.util.TreeSet<Integer> availableServers = new java.util.TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int maxRequests = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int currentLoad = load[i];
            
            // Free up all servers that have finished their tasks by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            // If all servers are busy, the request is dropped
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the preferred server using (i % k)
            int preferredServer = i % k;
            
            // Search for the first available server >= preferredServer
            Integer assignedServer = availableServers.ceiling(preferredServer);
            
            // If none found, wrap around and take the first available server from 0
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            
            // Assign the request to the server
            requestCount[assignedServer]++;
            maxRequests = Math.max(maxRequests, requestCount[assignedServer]);
            
            // Mark the server as busy and remove it from the available pool
            busyServers.offer(new int[]{arrivalTime + currentLoad, assignedServer});
            availableServers.remove(assignedServer);
        }
        
        // Collect all servers that matched the maximum request count
        java.util.List<Integer> result = new java.util.ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
