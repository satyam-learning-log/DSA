package Other_Algos_Graph;


import java.util.*;

public class G_56_Articulation_Point_in_Graph {
    private static int globalTimer = 1;


    public static void tarjansAlgo_DFS(int node, int parentNode, int[] visited, List<List<Integer>> adjacencyList, int[] tin, int[] low, int[] isArticulation) {

        visited[node] = 1;
        tin[node] = globalTimer;
        low[node] = globalTimer;
        globalTimer++;

        //visit the adjacents
        List<Integer> neighbors = adjacencyList.get(node);
        int childs = neighbors.size();

        for (int i = 0; i < neighbors.size(); i++) {

            int neighbor = neighbors.get(i);

            if (neighbor == parentNode) continue;

            if (visited[neighbor] != 1) {
                tarjansAlgo_DFS(neighbor, node, visited, adjacencyList, tin, low, isArticulation);
                low[node] = Math.min(low[node], low[neighbor]);

                //check if node is articulation node
                if (low[neighbor] >= tin[node] && parentNode != -1) {
                    isArticulation[node] = 1;
                }

            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }

        if(parentNode==-1 && childs>1){
            isArticulation[node] = 1;
        }

    }

    public  static List<Integer> articulationNodes(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjacencyList.add(new ArrayList<>());

        int[] visited = new int[n];    // visited
        int[] tin = new int[n];             // time of insertion
        int[] low = new int[n];             // lowest reachable time

        for (int i = 0; i < connections.size(); i++) {

            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);

        }
        int[] isArticulation = new int[n];


        tarjansAlgo_DFS(0, -1, visited, adjacencyList, tin, low, isArticulation);

        List<Integer> totalArticulationNodes = new ArrayList<>();
        for (int i = 0; i < isArticulation.length; i++) {
            if (isArticulation[i] == 1)
                totalArticulationNodes.add(i);
        }
        return totalArticulationNodes;
    }

    public static void main(String [] args){
        int n = 7;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(0,2));
        connections.add(Arrays.asList(0,3));
        connections.add(Arrays.asList(2,3));
        connections.add(Arrays.asList(2,4));
        connections.add(Arrays.asList(4,5));
        connections.add(Arrays.asList(5,6));
        connections.add(Arrays.asList(6,2));

         for(int node : articulationNodes(n, connections)){
             System.out.println(node);
         }


        }
}