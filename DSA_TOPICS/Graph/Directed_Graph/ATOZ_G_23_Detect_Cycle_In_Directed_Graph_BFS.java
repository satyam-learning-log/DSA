package Directed_Graph;

import java.util.*;

public class ATOZ_G_23_Detect_Cycle_In_Directed_Graph_BFS {
    // As per Algo_kahn's
    //if graph has cycle
    //queue will become empty without visiting all node's of graph
    //so at last if stack.size() != numCourses then we have cycle in our graph

    public static void buildAdjacencyListAndIndgree(int edges[][] , int n , int [] indgree , List<List<Integer>> adjacencyList){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            indgree[edges[i][1]]++;
        }

    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        int indgree[] = new int[numCourses];
        buildAdjacencyListAndIndgree(prerequisites, numCourses ,indgree , adjacencyList);
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<indgree.length; i++){
            if(indgree[i]==0) queue.offer(i);
        }

        // we couldn't find any start node
        if(queue.isEmpty()) {
            return new int[0];
        }

        int j=0;
        int result[] = new int[numCourses];
        while(!queue.isEmpty()){

            //pop element from queue
            int current = queue.poll();

            indgree[current] = -1;

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
            //   result[j++] = current;

        }


        // for(int i=0; i<indgree.length; i++){
        //     if(indgree[i]>-1) return new int[0];
        // }

        //  or

        if(stack.size()!=numCourses) return new int[0];



        while(!stack.isEmpty()){
            result[j]=stack.pop();
            j++;
        }


        return result;
    }

    public static  void main(String[] args){
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2},{4,3},{4,1},{0,4}};
        int numCourses = 5;
        int result [] = findOrder(numCourses, prerequisites);
        if( result.length==0) {
            System.out.println("Graph has cycle");
        }else{
        for(int ele : findOrder(numCourses,prerequisites )){
            System.out.println(ele);
        }
        }
    }

}
