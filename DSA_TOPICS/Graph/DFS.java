import java.util.ArrayList;
import java.util.List;

public class DFS {
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void buildAdjacencyList(int edges[][] , int n){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }

    }

    public static void DFS(int current, int[] visited, int edges[][], List<List<Integer>> adjacencyList){

        System.out.println(current);
        visited[current]=1;

        for(Integer neighbor : adjacencyList.get(current)){
            if(visited[neighbor] != 1){
                DFS(neighbor,visited,edges,adjacencyList);
            }
        }

    }

    public static void main(String [] args){
        int n = 9;
        int edges [][] = {{1,2},{1,3},{3,4},{3,7},{2,6},{2,5},{4,8},{7,8}};
        buildAdjacencyList(edges,n);
        int visited [] = new int[9];
        DFS(1, visited ,edges , adjacencyList );
    }
}
