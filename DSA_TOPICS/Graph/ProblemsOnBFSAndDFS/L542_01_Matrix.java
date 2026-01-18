package ProblemsOnBFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

public class L542_01_Matrix {
    public int[][] updateMatrix(int[][] mat) {
        //what is the aim ? - to find distance of nearest 0 for each cell .
        // if cell == 0 then distance would be 0
        // if cell == 1 ?

        //if we initially collect all cells who's value is equal to 0
        //and explore its adjacent level by level
        // we can find the distance (which is current level) and update the matrix where cell==1

        //aprroch
        // as level by level and as we need distance of nearest 0 cell we can't use dfs here
        // multiforce bfs is the right approch here


        // repeat this process until queue becomes empty

        int n = mat.length;
        int m = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int visited[][] = new int[n][m];
        int distance[][] = new int[n][m];

        //  initially collect all cells in a queue who's value is equal to 0
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j]==0){
                    queue.add(new int[]{i,j,0});
                    visited[i][j]=1;
                }
            }
        }

        // explore its adjacent (Horizontally and Vertically) , who's value is 1 and update current level as it's distance and add in queue for next level exploration .

        while(!queue.isEmpty()){

            int [] curr = queue.poll();
            int crow = curr[0];
            int ccol = curr[1];
            int dis  = curr[2];

            distance[crow][ccol] = dis;

            int nextx[] = new int[]{-1,0,1,0};
            int nexty[] = new int[]{0,-1,0,1};

            //explore adjacents where mat[i][j] == 1;
            for(int i=0; i<4; i++){
                if(crow+nextx[i]<0 || crow+nextx[i]>=mat.length || ccol+nexty[i]<0 || ccol+nexty[i]>=mat[0].length) continue;
                if(mat[crow+nextx[i]][ccol+nexty[i]]==1 && visited[crow+nextx[i]][ccol+nexty[i]]==0){
                    queue.add(new int[]{crow+nextx[i],ccol+nexty[i],dis+1});
                    visited[crow+nextx[i]][ccol+nexty[i]]=1;
                }
            }

        }

        return distance;



    }
}
