package ShortestPathAlgos;

import java.util.*;

public class ATOZ_G_32_Dijkstra_Algorithm_Using_TreeSet {

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

        TreeSet<List<Integer>> set = new TreeSet<>(
                (a,b)->{
                    if(!a.get(0).equals(b.get(0)))
                        return a.get(0) - b.get(0);
                    return a.get(1) - b.get(1);
        });

        //adding source
        set.add(Arrays.asList(0,0));
        cost[source]=0;


        while(!set.isEmpty()){

            List<Integer> current =set.pollFirst();

            int node = current.get(1);
            int costToReachCurrFromSource = current.get(0);

            List<int[]> neighbors = adjacencyList.get(node);

            for(int i=0; i<neighbors.size(); i++){

                int neighbor = neighbors.get(i)[0];
                int costToReachNeighbor = neighbors.get(i)[1];



                if(cost[neighbor]>costToReachCurrFromSource+costToReachNeighbor){

                    //remove entry from set
                    set.remove(Arrays.asList(cost[neighbor],neighbor));

                    int newCost = costToReachCurrFromSource+costToReachNeighbor;
                    cost[neighbor]=newCost;
                    set.add(Arrays.asList(newCost,neighbor));
                }


            }

        }
    }

    public static void main(String [] args){

       //   {to,cost}
         int n = 5;
        int edges[][][]={
                {{1,4},{2,100},{3,6},{4,7}}
                ,{{3,2},{2,6},{0,4}}
                ,{{3,8},{4,2},{1,6},{0,100}}
                ,{{4,4},{0,6},{1,2},{2,8}}
                ,{{0,7},{3,4},{2,2}}
        };

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
