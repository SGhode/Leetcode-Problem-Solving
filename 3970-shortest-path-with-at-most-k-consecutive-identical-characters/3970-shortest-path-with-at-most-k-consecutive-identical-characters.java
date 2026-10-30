class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        pq.offer(new int[]{0, 0, 1});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int u = curr[1];
            int curCount = curr[2];

            if (u == n - 1) {
                return cost;
            }

            if (cost > dist[u][curCount]) {
                continue;
            }

            for (int[] edge : g[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? curCount + 1 : 1;
                int nextCost = cost + weight;

                if (nextCount <= k && nextCost < dist[v][nextCount]) {
                    dist[v][nextCount] = nextCost;
                    pq.offer(new int[]{nextCost, v, nextCount});
                }
            }
        }

        return -1;
    }
}