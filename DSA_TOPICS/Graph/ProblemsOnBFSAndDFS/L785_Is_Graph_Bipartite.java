package ProblemsOnBFSAndDFS;


import java.util.*;

public class L785_Is_Graph_Bipartite {
    // what is meant by bipartite graph - if a graph doesn't contain cycle / if contain but count of edges in cycle is even
    public boolean DFS(int current , int parent , int [][] graph , int colorTracker[]){

        int neibors[] = graph[current];

        if(parent!=-1 && colorTracker[parent]==1){
            colorTracker[current] = 0;
        }else {
            colorTracker[current] = 1;
        }

        for(int i=0; i<neibors.length; i++){
            //not visited -> no problem
            if(colorTracker[neibors[i]]==-1){
                if(!DFS(neibors[i] ,  current ,  graph ,  colorTracker)) return false;
            }else if(colorTracker[neibors[i]]!=-1 && colorTracker[current] == colorTracker[neibors[i]]){
                //visited but color is a problem
                return false;
            }
            //visited but color is not a problem. - no call required /
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int colorTracker[] = new int[graph.length];
        Arrays.fill(colorTracker,-1);
        boolean bipartite = true;
        for(int i=0; i<graph.length; i++){
            if(colorTracker[i]==-1)
                bipartite &= DFS(i ,-1,graph,colorTracker);
        }
        return bipartite;
    }
}
