package ProblemsOnBFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AZ_Detect_Cycle_In_Graph_BFS {
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

    public static boolean detectCycleInGraph(int start , int visited[]){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start,-1});
        visited[start] = 1;

        while(!queue.isEmpty()){
            int currentPair[] = queue.poll();
            int current = currentPair[0];
            int parent = currentPair[1];
            List<Integer> neighbors = adjacencyList.get(current);
            for(int i=0; i<neighbors.size(); i++){
                int toAdd = neighbors.get(i);
                if(visited[toAdd]==1 && toAdd !=parent){
                    //detected cycle
                    return true;
                }else if(visited[toAdd]!=1){
                    queue.add(new int[]{toAdd,current});
                }
            }

        }

        return false;
    }
    public static void main(String[] args){
        int edges [][] = {{1,2},{1,3},{3,4},{3,6},{2,5},{5,7},{6,7}};
        int visited [] = new int[8];
        int start = 1;
        buildAdjacencyList(edges);
        System.out.println(detectCycleInGraph(start , visited));
    }
}
