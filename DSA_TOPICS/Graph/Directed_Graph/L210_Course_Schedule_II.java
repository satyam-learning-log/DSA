package Directed_Graph;


import java.util.*;

public class L210_Course_Schedule_II {

    public static boolean DFS( int current , List<List<Integer>> adjacencyList ,  Stack<Integer> stack , int path[] , int visited[]){

        visited[current] = 1;
        path[current] = 1;

        List<Integer> neighbors = adjacencyList.get(current);

        for(int i=0; i<neighbors.size(); i++){
            if(visited[neighbors.get(i)]!=1 && path[neighbors.get(i)]!=1){
                if(DFS( neighbors.get(i) ,  adjacencyList ,   stack , path ,visited)) return true;
            }else if(path[neighbors.get(i)]==1){
                return true;
            }
        }

        //3->1 , 3->2 , 1->0 , 2->0
        //ex., when we are at 0 we don't have any so we can add in the queue
        // 1 or 2 all neibors has been done so can add .
        // we can push into stack as it's all neighbors are visited (dependant)
        stack.push(current);
        path[current]=0;
        return false;

    }
    public static void buildAdjacencyList_DFS(int edges[][] , int n , List<List<Integer>> adjacencyList){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }

    }
    public static void buildAdjacencyListAndIndgree_BFS(int edges[][] , int n , int [] indgree , List<List<Integer>> adjacencyList){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            indgree[edges[i][1]]++;
        }

    }

    public static int[] findOrderBFSDFS(int numCourses, int[][] prerequisites , boolean isBFS) {

        if(isBFS) {
            List<List<Integer>> adjacencyList = new ArrayList<>();
            //ProblemsOnBFSAndDFS.BFS ->
            int indgree[] = new int[numCourses];
            buildAdjacencyListAndIndgree_BFS(prerequisites, numCourses, indgree, adjacencyList);
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < indgree.length; i++) {
                if (indgree[i] == 0) queue.offer(i);
            }

            if (queue.isEmpty()) {
                return new int[0];
            }

            int j = 0;
            int result[] = new int[numCourses];
            while (!queue.isEmpty()) {

                //pop element from queue
                int current = queue.poll();

                indgree[current] = -1;

                List<Integer> neighbors = adjacencyList.get(current);

                //explore neighbors , reduce neighbor's indgree and add it to queue if it's indgree becomes zero
                for (int i = 0; i < neighbors.size(); i++) {

                    int neighbor = neighbors.get(i);
                    indgree[neighbor]--;

                    if (indgree[neighbor] == 0) {
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

            if (stack.size() != numCourses) return new int[0];


            while (!stack.isEmpty()) {
                result[j] = stack.pop();
                j++;
            }


            return result;
        }



        //ProblemsOnBFSAndDFS.DFS ->

        //build adjacency list
        //check for cycle and put values in the stack
        //if cycle found return empty list

        if(!isBFS) {
            if (prerequisites.length == 0) return new int[1];

            List<List<Integer>> adjacencyList = new ArrayList<>();
            buildAdjacencyList_DFS(prerequisites, numCourses, adjacencyList);
            Stack<Integer> stack = new Stack<>();

            int result[] = new int[numCourses];

            int visited[] = new int[numCourses];
            int path[] = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                if (visited[i] != 1) {
                    if (DFS(i, adjacencyList, stack, path, visited)) return new int[0];
                }
            }

            int i = 0;
            while (!stack.isEmpty()) {
                result[i] = stack.pop();
                i++;
            }

            return result;
        }

        return new int[0];
    }

    public static void main(String[] args){
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        boolean isBFS = false;
        for(int ele : findOrderBFSDFS(numCourses,prerequisites , isBFS)){
            System.out.println(ele);
        }
    }
}
