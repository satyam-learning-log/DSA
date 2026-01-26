package Directed_Graph;

import java.util.*;

public class L802_Find_Eventual_Safe_States {
    // What question states ?
    // Return safe node (Node's who's every path leads to terminal node)
    // If we we think oppositly and reverse all edges in opposite direction
    // Return Nodes which will  not have an incoming edge
    // Then question comes down's to ProblemsOnBFSAndDFS.BFS - topological sort on a directed tree while queue is not Empty.

    public void createOppositeAdjancencyList(int numberOfNodes, int[][] graph, List<List<Integer>> adjacencyList ,  int[] indgree) {

        for (int i=0; i<numberOfNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i=0; i<graph.length; i++) {
            for (int j=0; j<graph[i].length; j++) {
                adjacencyList.get(graph[i][j]).add(i);
                indgree[i]++;
            }
        }

    }

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int numberOfNodes = graph.length;
        List<List<Integer>> adjacencyList = new ArrayList<>();
        int[] indgree = new int[numberOfNodes];
        List<Integer> outputList = new ArrayList<>();

        createOppositeAdjancencyList(numberOfNodes, graph, adjacencyList ,indgree);

        Queue<Integer> queue = new LinkedList<>();


        for(int i=0; i<indgree.length; i++){
            if(indgree[i]==0){
                queue.add(i);
            }
        }

        //ProblemsOnBFSAndDFS.BFS
        while(!queue.isEmpty()){

            //POP - each element
            // traverse its adjacents
            // reduce indgree of its adjacents and
            //if adjacents indgree becomes zero add in the queue (means in opposite - before reversal - all its adjacents (outdgree) can lead to terminated state)

            int node = queue.poll();

            List<Integer> neighbors = adjacencyList.get(node);

            for(int i=0; i<neighbors.size(); i++){
                indgree[neighbors.get(i)]--;
                if(indgree[neighbors.get(i)]==0) queue.offer(neighbors.get(i));
            }

            //outputList.add(node);

        }

        //Collections.sort(outputList);
        for(int i=0; i<indgree.length; i++)
            if(indgree[i]==0)outputList.add(i);

        return outputList;

    }
}
