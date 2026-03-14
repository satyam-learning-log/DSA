package MinimumSpanningTree_DisjointSetProblems;


import java.util.*;

public class L1584_Min_Cost_to_Connect_All_Points {
    public int minCostConnectPoints(int[][] points) {

        //create the adjacency list .
        //there are points.length == n - nodes .
        //we would need to represent it as vertex and calculate manhattan as distance .

        HashMap<Integer,HashMap<Integer,Integer>> adjancencyMap = new HashMap<>();

        int n = points.length;
        for(int i=0; i<n; i++) adjancencyMap.put(i,new HashMap<>());


        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int manhattanDis = Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                adjancencyMap.get(i).put(j,manhattanDis);
                adjancencyMap.get(j).put(i,manhattanDis);
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));

        // weight , vertex
        pq.add(new int[]{0,0});

        HashSet<Integer> visited = new HashSet<>();

        int MSTSum = 0;

        while(!pq.isEmpty()){

            int pair[] = pq.poll();
            int vertex = pair[1];
            int weight = pair[0];

            if(visited.contains(vertex)) continue;
            visited.add(vertex);

            MSTSum+=weight;

            //visiting the neighbors

            for(int neighbor : adjancencyMap.get(vertex).keySet()){

                if(!visited.contains(neighbor)){
                    int weightToVisit = adjancencyMap.get(vertex).get(neighbor);
                    pq.offer(new int[]{weightToVisit,neighbor});
                }

            }

        }

        return MSTSum;


    }
}
