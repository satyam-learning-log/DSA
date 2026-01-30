package ShortestPathAlgos;

import java.lang.reflect.Array;
import java.util.ArrayList;


import java.util.*;

public class ATOZ_G_32_Dijkstra_Algorithm_Using_Priority_Queue {


    public static void buildAdjacencyListWithCost(int n , int edges[][][] , List<List<int[]>> adjacencyList){
        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }
        for(int i=0; i<edges.length; i++){
            int neighbors [][] = edges[i];
            for(int j=0; j<neighbors.length; j++){
                int current [] = neighbors[j];
                int to = current[0];
                int cost = current[1];
                adjacencyList.get(i).add(new int[]{to,cost});
            }
        }
    }


    public static void Dijkstra_Algo(int source , List<List<int[]>> adjacencyList , int cost[]){

        // initially add source in the queue
        // iterate using loop while (q.isEmpty())
        // pop a node - pq picks smallest one
        // visit it's neighbors if cost[current]+cost[current->neighbor] < cost[neighbor]
        // add node in the queue

        Queue<int[]> queue = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        cost[source]=0;
        queue.offer(new int[]{source,cost[source]});


        while(!queue.isEmpty()){

            int current[] = queue.poll();

            int node = current[0];
            int costToReachCurrFromSource = current[1];

            List<int[]> neighbors = adjacencyList.get(node);

            for(int i=0; i<neighbors.size(); i++){

                int neighbor = neighbors.get(i)[0];
                int costToReachNeighbor = neighbors.get(i)[1];

                if(cost[neighbor]>costToReachCurrFromSource+costToReachNeighbor){
                    cost[neighbor]=costToReachCurrFromSource+costToReachNeighbor;
                    queue.offer(new int[]{neighbor,  cost[neighbor]});
                }
            }

        }
    }

    public static void main(String [] args){
        //build adjacency with cost
        //initialize cost with infinity
        //start recurrsive call from start
        //for current node visit its adjacents
        //update the cost
        //add in a priority queue

        //choose peak as source
        //mark current node as visited

        int n = 6;
        int edges[][][]={{{1,2},{2,8}},{{2,5},{3,8}},{{3,1}},{{4,4},{5,6}},{{5,10}}};

        List<List<int[]>> adjacencyList = new ArrayList<>();
        int cost [] = new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        buildAdjacencyListWithCost( n , edges, adjacencyList);

        Dijkstra_Algo(0,  adjacencyList ,cost);

        for(int i=0; i<cost.length; i++){
            System.out.println("Cost of "+i+" is "+cost[i]);
        }

    }
}
