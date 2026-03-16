package MinimumSpanningTree_DisjointSetProblems;


import java.util.*;

public class L947_Most_Stones_Removed_with_Same_Row_or_Column {
    public int findUltimateParent(int node, int[] parent) {
        if (node == parent[node])
            return node;
        return parent[node] = findUltimateParent(parent[node], parent);
    }

    public void union(int u, int v, int[] parent, int[] size) {

        int upu = findUltimateParent(u, parent);
        int upv = findUltimateParent(v, parent);

        if (upu == upv)
            return;

        if (size[upu] < size[upv]) {
            parent[upu] = upv;
            size[upv] += size[upu];

        } else {
            parent[upv] = upu;
            size[upu] += size[upv];
        }

    }

    public int removeStones(int[][] stones) {

        //find the row and col where vertex is present ;

        int maxrow = -1;
        int maxcol = -1;

        for (int i = 0; i < stones.length; i++) {
            maxrow = Math.max(maxrow, stones[i][0]);
            maxcol = Math.max(maxcol, stones[i][1]);
        }

        int parent[] = new int[maxrow + maxcol + 2];
        int size[] = new int[maxrow + maxcol + 2];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        HashSet<Integer> vertex = new HashSet<>();

        for (int i = 0; i < stones.length; i++) {
            int pair[] = stones[i];
            union(pair[0], maxrow + pair[1] + 1, parent, size);
            vertex.add(pair[0]);
            vertex.add(maxrow + pair[1] + 1);
        }

        // HashSet<Integer> ultimateParents = new HashSet<>();

        int ultimateParents = 0;

        for (int node : vertex) {
            // ultimateParents.add(findUltimateParent(node,parent));
            if (findUltimateParent(node, parent) == node)
                ultimateParents++;
        }

        // System.out.println(ultimateParents.size());
        // return stones.length-ultimateParents.size();

        return stones.length - ultimateParents;

        //n^2 - not optimal

        //create the adjacency list
        // Map<Integer,List<Integer>> adjacencyMap = new HashMap<>();

        // int n = stones.length;

        // for(int i=0; i<n; i++) adjacencyMap.put(i,new ArrayList<>());

        // List<int[]> edges = new ArrayList<>();

        // for(int i=0; i<n; i++){
        //     for(int j=i+1; j<n; j++){
        //         // 00
        //         int icor [] = stones[i];
        //         // 01
        //         int jcor [] = stones[j];

        //         // create an edge and add in adjacency list if it belongs to same row or same column
        //         if(icor[0]==jcor[0] || icor[1]==jcor[1]){
        //             adjacencyMap.get(i).add(j);
        //             adjacencyMap.get(j).add(i);
        //             edges.add(new int[]{i,j});
        //         }

        //     }
        // }

        // //disjoint set

        // int size [] = new int[n];
        // int parent [] = new int[n];

        // for(int i=0; i<n; i++){
        //     size[i]=1;
        //     parent[i]=i;
        // }

        // for(int i=0; i<edges.size(); i++){
        //     int edge[] = edges.get(i);
        //     union(edge[0],edge[1],parent,size);
        // }

        // Set<Integer> uniqueUltimateParents = new HashSet<>();

        // for(int i=0; i<n; i++){
        //     uniqueUltimateParents.add(findUltimateParent(parent[i],parent));
        // }

        // return n-uniqueUltimateParents.size();

    }
}
