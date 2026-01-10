import java.util.ArrayList;
import java.util.List;

public class L547_Number_of_ProvincesxConnectedComponents {

    static List<List<Integer>> adjacencyList = new ArrayList<>();

    private static void buildAdjacencyList(int edges[][]){
        for(int i=0; i<6; i++){
            adjacencyList.add(new ArrayList<>());
        }
        for(int i=0; i<edges.length;i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }
    }
    public static void main(String[] args){
        System.out.println("Example of Connected components using Adjacency List");
        int n = 6;
        int edges [][] = {{0,1},{1,2},{2,3},{4,5}};
        buildAdjacencyList(edges);
        System.out.println(countAndReturnConnectedComponents(edges,adjacencyList,n));

        System.out.println("Example of Connected components using Adjacency Matrix and Traversal on it directly");
        int [][] isConnected = {{1,1,1,0,0},{1,1,1,0,0},{1,1,1,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(findCircleNum( isConnected));

    }

    private static int countAndReturnConnectedComponents(int[][] edges, List<List<Integer>> adjacencyMatric , int n) {

        int [] visited = new int[6];
        int count = 0;
        for(int i=0; i<n; i++){
            if(visited[i]==0){
                visit(i,visited);
                count++;
            }
        }

        return count;
    }

    private static int findCircleNum(int[][] isConnected) {

        int visited[] = new int[isConnected.length];

        int count = 0;

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] != 1) {
                visit(i, visited, isConnected);
                count++;
            }
        }

        return count;

    }

    private static void visit(int start , int visited [] , int[][] isConnected ){
        visited[start]=1;

        for(int j=0; j<isConnected.length; j++){
            if(isConnected[start][j] ==1  && visited[j]!=1 && start !=j ){
                visit(j,visited,isConnected);
            }
        }
    }

    private static void visit(int start , int [] visited ) {
        visited[start]=1;
        List<Integer> toVisit = adjacencyList.get(start);
        for(int i=0; i<toVisit.size(); i++){
            if(visited[toVisit.get(i)]==0)
            visit(toVisit.get(i),visited);
        }

    }

}
