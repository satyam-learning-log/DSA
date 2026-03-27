package MinimumSpanningTree_DisjointSetProblems;

import java.util.*;

public class L827_Making_A_Large_Island {

        class Disjoint_Set_By_Size {

            int[] size;
            int[] parent;

            Disjoint_Set_By_Size(int no_of_nodes){

                size = new int[no_of_nodes];
                parent = new int[no_of_nodes];
                for(int i=0; i<no_of_nodes; i++) {
                    parent[i] = i;
                    size[i]=1;
                }

            }

            public int findUltimateParent(int node) {

                if (node == parent[node]) {
                    return node;
                }

                parent[node] = findUltimateParent(parent[node]);
                return parent[node];
            }

            public void unionBySize(int u, int v) {

                // check ultimate parent of u and v
                int upu = findUltimateParent(u);
                int upv = findUltimateParent(v);

                // if both belong to same parent
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
        }

        public int largestIsland(int[][] grid) {

            int n = grid.length;

            //edge case
            int countnoof1 = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        countnoof1++;
                    }
                }
            }

            if (countnoof1 == 0)
                return 1;

            if (countnoof1 == n * n)
                return n * n;

            //declaring disjoint set
            Disjoint_Set_By_Size ds = new Disjoint_Set_By_Size(n*n);

            //initializing disjoint set
            for (int i = 0; i<n; i++) {
                for (int j = 0; j<n; j++) {

                    int nexti[] = { -1, 0, 1, 0 };
                    int nextj[] = { 0, -1, 0, 1 };

                    for (int k = 0; k < 4; k++) {
                        //try 4 direction
                        if (nexti[k] + i >= 0 && nexti[k]+i<n && nextj[k]+j>= 0 && nextj[k]+j<n) {
                            int u = i * n + j;
                            int v = (nexti[k]+i)*n + (nextj[k]+j);

                            if (grid[i][j] == 1 && grid[(nexti[k]+i)][(nextj[k]+j)] == 1) {
                                ds.unionBySize(u, v);
                            }
                        }
                    }

                }
            }

            int maxareaafter0to1 = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (grid[i][j] == 0) {

                        int nexti[] = { -1, 0, 1, 0 };
                        int nextj[] = { 0, -1, 0, 1 };

                        int sizeOfAdjacentComponent = 0;

                        HashSet<Integer> parents = new HashSet<>();

                        for (int k = 0; k < 4; k++) {
                            //try 4 direction
                            if (nexti[k] + i >= 0 && nexti[k] + i < n && nextj[k]+j >= 0 && nextj[k]+j<n &&
                                    grid[nexti[k]+i][nextj[k]+j]==1) {

                                int u = i * n + j;
                                int v = (nexti[k] + i) * n + (nextj[k] + j);

                                parents.add(ds.findUltimateParent(v));

                            }
                        }
                        for(int parent : parents){
                            sizeOfAdjacentComponent+=ds.size[parent];
                        }
                        sizeOfAdjacentComponent++;
                        maxareaafter0to1 = Math.max(sizeOfAdjacentComponent, maxareaafter0to1);
                    }

                }
            }

            return maxareaafter0to1;

        }

}
