package ShortestPathAlgos;


//Intution -
//Since all edges have equal weight, visiting nodes level by level from the source gives the shortest path.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class ATOZ_G_28_Shortest_Path_in_Undirected_Graph {

    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void buildAdjacencyList(int n , int edges[][]){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }

    }

    public static void BFS(int start , List<List<Integer>> adjacencyList , int cost []){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        int level=0;

        while (!queue.isEmpty()){

            int size = queue.size();

            for(int j=0; j<size; j++) {
                int current = queue.poll();

                List<Integer> neighbors = adjacencyList.get(current);

                for (int i = 0; i < neighbors.size(); i++) {

                    int neighbor = neighbors.get(i);

                    if (cost[neighbor] == Integer.MAX_VALUE) {
                        cost[neighbor] = level + 1;
                        queue.add(neighbor);
                    }

                }
            }
            level++;

        }


    }

    public static void main(String [] args){
        int n = 9;
        int edges[][] = new int[][]{{0, 1},{0, 3},{1, 2},{3, 4},{4, 5},{2, 6},{5, 6},{6, 7},{6, 8},{7, 8}};
        int cost []  = new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        int start=0;
        cost[start]=0;
        buildAdjacencyList(n , edges);
        BFS(0 ,  adjacencyList , cost );
        for(int i =0; i<cost.length; i++){
            System.out.println(" Cost for  -> " +i+" is "+cost[i] );
        }


    }
}

