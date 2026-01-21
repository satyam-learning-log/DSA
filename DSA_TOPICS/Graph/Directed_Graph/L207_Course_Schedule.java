package Directed_Graph;

import java.util.*;

public class L207_Course_Schedule {
    public static void buildAdjacencyList(int edges[][] , int n ,  List<List<Integer>> adjacencyList){

        for(int i=0; i<n; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][1]).add(edges[i][0]);
        }

    }

    public static boolean DFS(int current , List<List<Integer>> adjacencyList, int visited[], int path[]){
        visited[current]=1;
        path[current]=1;

        List<Integer> neibors = adjacencyList.get(current);
        for(int i=0; i<neibors.size(); i++){
            if(visited[neibors.get(i)]!=1 && path[neibors.get(i)]!=1){
                if(DFS( neibors.get(i),  adjacencyList, visited, path)) return true;
            }else if(path[neibors.get(i)]==1){
                return true;
            }
        }
        path[current]=0;
        return  false;
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if(prerequisites.length==0) return true;

        List<List<Integer>> adjacencyList = new ArrayList<>();
        buildAdjacencyList(prerequisites,numCourses,adjacencyList);

        int visited[] = new int[numCourses];
        int path[] = new int[numCourses];

        for(int i=0; i<visited.length; i++){
            if(visited[i]!=1){
                if(DFS(i,  adjacencyList, visited, path)) {
                    return false;
                }
            }

        }
        return true;
    }
}
