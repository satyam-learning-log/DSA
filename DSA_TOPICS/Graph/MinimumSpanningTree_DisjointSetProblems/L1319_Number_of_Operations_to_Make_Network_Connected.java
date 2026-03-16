package MinimumSpanningTree_DisjointSetProblems;

import java.util.*;

public class L1319_Number_of_Operations_to_Make_Network_Connected {

    // Find parent with path compression
    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // Union by rank
    private void union(int[] parent, int[] rank, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);
        if (px == py) return;

        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }

    // Function to find minimum operations to make the graph connected
    public int makeConnected(int n, int[][] connections) {
        // If not enough edges, return -1
        if (connections.length < n - 1) return -1;

        int[] parent = new int[n];
        int[] rank = new int[n];

        // Initialize parent
        for (int i = 0; i < n; i++) parent[i] = i;

        // Union all edges
        for (int[] edge : connections)
            union(parent, rank, edge[0], edge[1]);

        // Count unique parents
        Set<Integer> components = new HashSet<>();
        for (int i = 0; i < n; i++)
            components.add(find(parent, i));

        return components.size() - 1;
    }

}
