class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: [weight, index]
        java.util.PriorityQueue<long[]> freeServers = new java.util.PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        );

        // Min-heap for busy servers: [freeTime, weight, index]
        java.util.PriorityQueue<long[]> busyServers = new java.util.PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            freeServers.offer(new long[]{servers[i], i});
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] server = busyServers.poll();
                freeServers.offer(new long[]{server[1], server[2]});
            }

            long[] bestServer = freeServers.poll();
            ans[j] = (int) bestServer[1];
            busyServers.offer(new long[]{currentTime + tasks[j], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}
