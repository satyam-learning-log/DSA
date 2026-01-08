import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {

    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void buildAdjacencyList(int edges[][]){
        for(int i=0; i<6; i++){
            adjacencyList.add(new ArrayList<>());
        }
        for(int i=0; i<edges.length;i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }
    }
    public static void main(String [] args){
        int n = 6;
        int edges [][] = {{0,1},{1,2},{2,3},{4,5}};
        buildAdjacencyList(edges);
        System.out.println(countAndReturnConnectedComponents(edges,adjacencyList,n));
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

    private static void visit(int start , int [] visited ) {
        visited[start]=1;
        List<Integer> toVisit = adjacencyList.get(start);
        for(int i=0; i<toVisit.size(); i++){
            if(visited[toVisit.get(i)]==0)
            visit(toVisit.get(i),visited);
        }

    }

}
