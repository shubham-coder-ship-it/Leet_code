class Solution {
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private int[] tree;
    private int[] nodeValues;
    private int[] seq; // Maps segment tree positions back to node values

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // 1. Build adjacency list safely
        java.util.List<java.util.List<Integer>> adj = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new java.util.ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // 2. Initialize HLD arrays
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        seq = new int[n];
        nodeValues = new int[n];
        
        for (int i = 0; i < n; i++) {
            heavy[i] = -1;
            nodeValues[i] = 1 << (s.charAt(i) - 'a');
        }

        // 3. Run HLD traversals
        dfsSize(0, -1, 0, adj);
        curPos = 0;
        dfsDecompose(0, 0, adj);

        // 4. Build the Segment Tree with mapped leaf values
        tree = new int[4 * n];
        buildTree(1, 0, n - 1);

        // 5. Process Queries
        List<Boolean> answer = new java.util.ArrayList<>(queries.length);
        for (String q : queries) {
            if (q.charAt(0) == 'u') { // "update ui c"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                char c = q.charAt(secondSpace + 1);
                
                int newMask = 1 << (c - 'a');
                updateTree(1, 0, n - 1, pos[u], newMask);
            } else { // "query ui vi"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = 0, v = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                for (int i = secondSpace + 1; i < q.length(); i++) {
                    v = v * 10 + (q.charAt(i) - '0');
                }

                int pathXor = queryPath(u, v, n);
                answer.add(Integer.bitCount(pathXor) <= 1);
            }
        }

        return answer;
    }

    private int dfsSize(int u, int p, int d, java.util.List<java.util.List<Integer>> adj) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtree = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                int subtreeSize = dfsSize(v, u, d + 1, adj);
                size += subtreeSize;
                if (subtreeSize > maxSubtree) {
                    maxSubtree = subtreeSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfsDecompose(int u, int h, java.util.List<java.util.List<Integer>> adj) {
        head[u] = h;
        pos[u] = curPos;
        seq[curPos] = nodeValues[u];
        curPos++;
        
        if (heavy[u] != -1) {
            dfsDecompose(heavy[u], h, adj);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                dfsDecompose(v, v, adj);
            }
        }
    }

    private void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = seq[start];
            return;
        }
        int mid = (start + end) >> 1;
        buildTree(2 * node, start, mid);
        buildTree(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void updateTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) >> 1;
        if (idx <= mid) {
            updateTree(2 * node, start, mid, idx, val);
        } else {
            updateTree(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) >> 1;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPath(int u, int v, int n) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= queryTree(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= queryTree(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}
