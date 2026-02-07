package ShortestPathAlgos;

import java.util.*;

public class ATOZ_G_41_Bellman_Ford_Algorithm {
    public static void bellmonfordAlgo(int edges[][],int nodes,int cost[]){
        //do n iterations if at nth iteration any update happend
        //then print graph has negative cycles

        boolean negativeCycle = false;

        for(int i=0; i<=nodes; i++){
            for(int j=0; j<edges.length; j++){
                int from = edges[j][0];
                int to = edges[j][1];
                int costtovist = edges[j][2];
                if(cost[from]!=Integer.MAX_VALUE){
                    if(cost[to]>cost[from]+costtovist){
                        if(i==nodes) negativeCycle = true;
                        cost[to] = cost[from]+costtovist;
                    }
                }
            }
        }

        if(negativeCycle){
            System.out.println("Negative cycle exists");
        }else{
            for(int i=0; i<cost.length; i++){
                System.out.println("Cost for " + i + cost[i]);
            }
        }

    }
    public static void main(String [] args){

        int negativeedgesgraph[][] = {{2,1,-3},{1,3,2},{0,1,2},{0,2,4}};
        int negativeedgesgraphwithcycle[][] = {{3,2,2},{2,1,-3},{1,3,2},{0,1,2},{0,2,4}};
        int negativeedgesgraphwithnegativecycle[][] = {{3,2,2},{2,1,-5},{1,3,2},{0,1,2},{0,2,4}};

        int nodes = 4;
        int cost[] = new int[nodes];
        Arrays.fill(cost,Integer.MAX_VALUE);
        int source = 0;
        cost[source]=0;
        bellmonfordAlgo(negativeedgesgraphwithnegativecycle,nodes,cost);
    }
}
