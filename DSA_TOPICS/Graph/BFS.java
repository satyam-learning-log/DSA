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



    public static void main(String [] args){
        int n = 7;
        int m = 6;
        //int edges [][] = {{1,2},{1,3},{2,6},{2,7},{3,4},{4,5}};
        int edges [][] = {{1,2},{1,3},{3,4},{2,5},{6,4},{5,6}};
        int visited [] = new int[7];
        Queue<Integer> queue = new LinkedList<>();
        int start = 1;

        buildAdjacencyList(edges);
        queue.add(start);
        visited[start] = 1;
        while(!queue.isEmpty()){

            int element = queue.poll();

            System.out.println(element);

            List<Integer> tovisit = adjacencyList.get(element);

            for(int i=0; i<tovisit.size(); i++){
                if(visited[tovisit.get(i)]!=1){
                    queue.offer(tovisit.get(i));
                    visited[tovisit.get(i)] = 1;
                }
            }

        }

    }
}


// Questions ?
// why adjacency list required bcoz we don't have node similar to tree
