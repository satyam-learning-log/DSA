import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void buildAdjacencyList(int edges[][]){

        for(int i=0; i<8; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }

    }

    private static void BFSonAdjList(int start , Queue<Integer> queue  , int visited [] ) {
        queue.add(start);
        visited[start] = 1;
        while (!queue.isEmpty()) {

            int element = queue.poll();

            System.out.println(element);

            List<Integer> tovisit = adjacencyList.get(element);

            for (int i = 0; i < tovisit.size(); i++) {
                if (visited[tovisit.get(i)] != 1) {
                    queue.offer(tovisit.get(i));
                    visited[tovisit.get(i)] = 1;
                }
            }

        }
    }


    private static void BFSOnAdjMatrix(int start , Queue<Integer> queue  , int visited [] , int [][] AdjMatrix ){
        queue.add(start);
        visited[start]=1;

        while(!queue.isEmpty()){
            int vertex = queue.poll();
            System.out.println(vertex);

            for(int i=vertex; i<AdjMatrix.length; i++){
                if(visited[i]!=1){
                    queue.offer(i);
                    visited[i]=1;
                }
            }
        }
    }


    public static void main(String[] args){
        int n = 7;
        int m = 6;
        //int edges [][] = {{1,2},{1,3},{2,6},{2,7},{3,4},{4,5}};
        int edges [][] = {{1,2},{1,3},{3,4},{2,5},{6,4},{5,6}};
        int visited [] = new int[7];
        Queue<Integer> queue = new LinkedList<>();
        int start = 1;

        buildAdjacencyList(edges);
        System.out.println("BFS on Adjacency List");
        BFSonAdjList(start,queue,visited);

        int AdjMatrix [][] = {{0,1,1},{1,0,1},{1,1,0}};
        int AdjMatrixStart = 0;
        int AdjMatrixVisited[] = new int[AdjMatrix.length];
        System.out.println("BFS on Adjacency Matrix");
        BFSOnAdjMatrix(AdjMatrixStart ,  queue  ,AdjMatrixVisited ,  AdjMatrix );


    }
}


// Questions ?
// why adjacency list required bcoz we don't have node similar to tree
