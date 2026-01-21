package Directed_Graph;

import java.util.*;

public class DFS {
    static List<List<Integer>> adjacencyList = new ArrayList<>();


    public static void buildAdjacencyList(int edges[][] , int n){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
        }

    }

    public static void DFS(int current , List<List<Integer>> adjacencyList, int visited[], int path[]){
        visited[current]=1;
        path[current]=1;
        System.out.println("Visited "+current);
        List<Integer> neibors = adjacencyList.get(current);
        for(int i=0; i<neibors.size(); i++){
            if(visited[neibors.get(i)]!=1 && path[neibors.get(i)]!=1){
                DFS( neibors.get(i),  adjacencyList, visited, path);
            }
        }
        path[current]=0;

    }


    public static void main(String [] args){

        List<List<Integer>> list = new ArrayList<>();
        int edges [][] = {{1,2},{2,3},{3,4},{4,5},{5,6},{3,7},{7,5},{8,2},{8,9},{9,10},{10,8}};
        buildAdjacencyList(edges,11);

        int visited[] = new int[11];
        int path[] = new int[11];

        for(int i=1; i<visited.length; i++){
            if(visited[i]!=1)
            DFS(i,  adjacencyList, visited, path);
        }


    }
}
