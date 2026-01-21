package ProblemsOnBFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AZ_Detect_Cycle_In_Graph_DFS {
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
    public static boolean detectCycleInGraph(int node , int parent , int visited[] , List<List<Integer>> adjacencyList ){
        List<Integer> neighbors = adjacencyList.get(node);
        visited[node]=1;
        for(int i=0; i<neighbors.size(); i++){
           if(visited[neighbors.get(i)]!=1){
               if(detectCycleInGraph(neighbors.get(i) ,  node , visited ,  adjacencyList )) return true;
           }else if(neighbors.get(i)!=parent){
               return true;
           }
        }
       return false;
    }
    public static void main(String[] args){
        //added a edge to test is it handle self loop
        int edges [][] = {{1,2},{1,1},{1,3},{3,4},{3,6},{2,5},{5,7},{6,7}};
        int visited [] = new int[8];
        int start = 1;
        buildAdjacencyList(edges);
        if(detectCycleInGraph(start , -1,  visited,adjacencyList))
            System.out.println("Cycle found");

    }
}
