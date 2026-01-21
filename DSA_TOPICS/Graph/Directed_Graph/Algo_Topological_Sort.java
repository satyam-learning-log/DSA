package Directed_Graph;


import  java.util.*;
public class Algo_Topological_Sort {

    static List<List<Integer>> adjacencyList = new ArrayList<>();


    public static void buildAdjacencyList(int edges[][] , int n){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
        }

    }

    public static void DFS(int current , Set<Integer> visited  ,Stack<Integer> stack , List<List<Integer>> adjacencyList ){
        visited.add(current);
        List<Integer> currentList = adjacencyList.get(current);

        for(int i=0; i<currentList.size(); i++){
            if(!visited.contains(currentList.get(i))){
                DFS(currentList.get(i),visited,stack,adjacencyList);
            }
        }

        stack.push(current);
    }

    public static void main(String[] agrs){
        int edges [][] = {{5,0},{4,0},{5,2},{4,1},{2,3},{3,1}};
        buildAdjacencyList(edges,6);
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<6; i++){
            if(!visited.contains(i)){
                DFS(i,visited,stack,adjacencyList);
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }


}
