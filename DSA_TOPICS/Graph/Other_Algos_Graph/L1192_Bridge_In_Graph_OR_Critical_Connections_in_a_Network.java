package Other_Algos_Graph;


import java.util.*;

public class L1192_Bridge_In_Graph_OR_Critical_Connections_in_a_Network {
    private int insertTimeGobal = 1;

    public void tarjansAlgo_DFS(int current , int parentNode ,int insertionTime , int visited[], List<List<Integer>> adjacencyList , int insertion[] , int minInsertionOfAdjancentCanReach[],List<List<Integer>> bridges){

        visited[current] = 1;
        List<Integer> neighbors = adjacencyList.get(current);
        insertion[current] = insertTimeGobal;
        minInsertionOfAdjancentCanReach[current] = insertTimeGobal;

        insertTimeGobal++;

        for(int i=0; i<neighbors.size(); i++){

            // if neighbor == parent continue;
            int neighbor = neighbors.get(i);

            if(neighbor==parentNode) continue;

            if(visited[neighbor]!=1){
                tarjansAlgo_DFS(neighbor , current, insertionTime , visited, adjacencyList , insertion, minInsertionOfAdjancentCanReach,bridges);
            }

            minInsertionOfAdjancentCanReach[current] = Math.min(minInsertionOfAdjancentCanReach[current],minInsertionOfAdjancentCanReach[neighbor]);

            //check can this edge become the bridge
            if(insertion[current]<minInsertionOfAdjancentCanReach[neighbor]){
                //its a bridge
                bridges.add(List.of(current,neighbor));
            }

        }

    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i<n; i++) adjacencyList.add(new ArrayList<>());

        List<List<Integer>> bridges = new ArrayList<>();
        int visited[] = new int[n];
        int minInsertionOfAdjancentCanReach[] = new int[n];
        int insertion[] = new int[n];

        for(int i=0; i<connections.size(); i++){

            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);

        }

        tarjansAlgo_DFS(0,-1 , 0 ,visited , adjacencyList ,  insertion ,  minInsertionOfAdjancentCanReach,bridges);

        return bridges;
    }
}
