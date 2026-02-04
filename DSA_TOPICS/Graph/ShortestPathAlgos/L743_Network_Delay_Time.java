package ShortestPathAlgos;


import java.util.*;

public class L743_Network_Delay_Time {
    public void buildAdjacencyList(List<List<int[]>> adjacencyList , int[][] times , int n){

        for(int i=0; i<n+1; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<times.length; i++){

            int curr[] = times[i];

            int from = curr[0];
            int to = curr[1];
            int time = curr[2];

            adjacencyList.get(from).add(new int[]{to,time});

        }


    }

    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> adjacencyList = new ArrayList<>();

        buildAdjacencyList(adjacencyList,times,n);

        int timereq[] = new int[n+1];
        Arrays.fill(timereq,Integer.MAX_VALUE);
        timereq[k]=0;

        Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        pq.offer(new int[]{0,k});

        while(!pq.isEmpty()){

            //poll a node from queue
            int node[] = pq.poll();

            int timetoreachcurr = node[0];
            int curr = node[1];

            if(timetoreachcurr>timereq[curr]) continue;

            List<int[]> neighbors = adjacencyList.get(curr);

            for(int i=0; i<neighbors.size(); i++){

                int neighbor[] = neighbors.get(i);

                if(timetoreachcurr+neighbor[1]<timereq[neighbor[0]]){
                    timereq[neighbor[0]] = timetoreachcurr+neighbor[1];
                    pq.offer(new int[]{timereq[neighbor[0]],neighbor[0]});
                }

            }

        }

        int minCost = 0;

        for(int i=1; i<timereq.length; i++){
            System.out.println(timereq[i]);
            minCost = Math.max(timereq[i],minCost);
        }

        return (minCost==Integer.MAX_VALUE)?-1:minCost;

    }
}
