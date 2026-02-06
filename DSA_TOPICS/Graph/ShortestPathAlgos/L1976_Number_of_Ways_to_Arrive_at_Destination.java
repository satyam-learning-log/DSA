package ShortestPathAlgos;


import java.util.*;

public class L1976_Number_of_Ways_to_Arrive_at_Destination {
    public static void buildAdjacencyListWithCost(int n, int[][] roads,
                                                  List<List<int[]>> adj){
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i=0; i<roads.length; i++){
            int from = roads[i][0];
            int to = roads[i][1];
            int dis = roads[i][2];
            adj.get(from).add(new int[]{to,dis});
            adj.get(to).add(new int[]{from,dis});

        }

    }

    public int countPaths(int n, int[][] roads) {

        int MOD = 1_000_000_007;

        if (n == 1) return 1;

        long dis[] = new long[n];
        Arrays.fill(dis,Long.MAX_VALUE);
        dis[0]=0;

        int minWays[] = new int[n];
        minWays[0]=1;

        List<List<int[]>> adj = new ArrayList<>();
        buildAdjacencyListWithCost(n, roads, adj);

        Queue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[1],b[1]));
        pq.offer(new long[]{0,0});

        while (!pq.isEmpty()) {

            //poll a node
            long[] cur = pq.poll();
            int currnode = (int) cur[0];
            long currdis = cur[1];


            if (currdis > dis[currnode]) continue;

            //explore the neighbors
            List<int[]> neighbors  = adj.get(currnode);


            for(int i=0; i<neighbors.size(); i++){

                int [] neighbor = neighbors.get(i);

                int currneighbor = (int)neighbor[0];

                long distancetoreachneighbor = neighbor[1];

                if(dis[currneighbor]>distancetoreachneighbor+currdis){
                    dis[currneighbor] = distancetoreachneighbor+currdis;
                    minWays[currneighbor] = minWays[currnode];
                    pq.add(new long[]{currneighbor,distancetoreachneighbor+currdis});

                }else if(dis[currneighbor]==distancetoreachneighbor+currdis){
                    //node has mutiple paths with same min.distance
                    minWays[currneighbor] = (minWays[currneighbor]+minWays[currnode])%MOD;
                }

            }



        }

        return minWays[n - 1];
    }
}
