package Other_Algos_Graph;

//algo
//apply toposort and collect vertexs based on finish time
//reverse edges of the graph
//pop each node from output of toposort stored in the stack
//apply dfs on each unvisited node and print nodes for each call

import java.util.*;

public class G_60_Kosarajus_Algorithm_Single_Source_Component {
    public static void buildAdjacencyList(int[][] edges,int n ,List<List<Integer>>adjacencyList,boolean isReverse){
        for(int i=0; i<n; i++) adjacencyList.add(new ArrayList<>());

        for(int i=0; i<edges.length; i++){

            int u = edges[i][0];
            int v = edges[i][1];
            if(isReverse){
                adjacencyList.get(v).add(u);

            }else{
                adjacencyList.get(u).add(v);

            }
        }

    }

    public static void toposort(int node ,Stack<Integer> topo_stack , int visited[], List<List<Integer>>adjacencyList){
        visited[node] = 1;
        List<Integer> neighbors = adjacencyList.get(node);

        for(int i=0; i<neighbors.size(); i++){
            int neighbor = neighbors.get(i);
            if(visited[neighbor]!=1){
                toposort(neighbor , topo_stack , visited, adjacencyList);
            }
        }
        topo_stack.push(node);
    }
    public static void topoSortOnReversedGraph(int node ,Stack<Integer> topo_stack , List<Integer> scc,int visited[], List<List<Integer>>adjacencyList){
        visited[node] = 1;
        List<Integer> neighbors = adjacencyList.get(node);
        scc.add(node);

        for(int i=0; i<neighbors.size(); i++){
            int neighbor = neighbors.get(i);
            if(visited[neighbor]!=1){
                topoSortOnReversedGraph(neighbor , topo_stack ,scc, visited, adjacencyList);
            }
        }

    }
    public static void main(String [] args){

        int n = 6;
        boolean isReverse = false;
        int[][] edges = new int[][]{{0,1},{1,2},{2,1},{2,4},{4,5}};
        List<List<Integer>>adjacencyList = new ArrayList<>();
        buildAdjacencyList(edges,n,adjacencyList,isReverse);

        Stack<Integer> topo_stack = new Stack<Integer>();
        int visited[] = new int[n];
        int start_node = 2;
        toposort(start_node ,topo_stack,visited,adjacencyList);

        adjacencyList = new ArrayList<>();
        Arrays.fill(visited,0);
        isReverse = true;
        buildAdjacencyList(edges,n,adjacencyList,isReverse);

        List<List<Integer>> result = new ArrayList<>();

        while(!topo_stack.isEmpty()){
           int  node = topo_stack.pop();
            if(visited[node]!=1){
                List<Integer> scc = new ArrayList<>();
                topoSortOnReversedGraph(node ,topo_stack , scc,visited, adjacencyList);
                result.add(scc);
            }
        }

        for(List<Integer> scc : result ){
            System.out.println(scc);
        }


    }
}
