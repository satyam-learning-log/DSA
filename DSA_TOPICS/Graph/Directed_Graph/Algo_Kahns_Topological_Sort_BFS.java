package Directed_Graph;

import java.util.*;

public class Algo_Kahns_Topological_Sort_BFS {
    static List<List<Integer>> adjacencyList = new ArrayList<>();


    public static void buildAdjacencyListAndIndgree(int edges[][] , int n , int [] indgree ){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            indgree[edges[i][1]]++;
        }


    }

    public static void main(String[] agrs) {

        int edges[][] = {{5, 0}, {4, 0}, {5, 2}, {4, 1}, {2, 3}, {3, 1}};

        int indgree[] = new int[6];
        buildAdjacencyListAndIndgree(edges, 6 ,indgree);
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        //ProblemsOnBFSAndDFS.BFS
        for(int i=0; i<indgree.length; i++){
            if(indgree[i]==0) queue.offer(i);
        }

        if(queue.isEmpty()) {
            System.out.println(" Graph contains cycle");
        }

        while(!queue.isEmpty()){

            //pop element from queue
            int current = queue.poll();

            List<Integer> neighbors = adjacencyList.get(current);

            //explore neighbors , reduce neighbor's indgree and add it to queue if it's indgree becomes zero
            for(int i=0; i<neighbors.size(); i++){

                int neighbor = neighbors.get(i);
                indgree[neighbor]--;

                if(indgree[neighbor]==0){
                    queue.offer(neighbor);
                }
            }

            stack.push(current);

        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }


    }
}
