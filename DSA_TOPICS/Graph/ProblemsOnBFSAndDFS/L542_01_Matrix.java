package ProblemsOnBFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

public class L542_01_Matrix {
    public int[][] updateMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int visited[][] = new int[n][m];
        int distance[][] = new int[n][m];

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==0){
                    queue.add(new int[]{i,j,0});
                    visited[i][j]=1;
                }

            }
        }


        while(!queue.isEmpty()){

            int current [] = queue.poll();

            int row = current[0];
            int col = current[1];
            int dis = current[2];

            distance[row][col]=dis;

            int x[] = new int[]{-1,0,1,0};
            int y[] = new int[]{0,-1,0,1};


            for(int i=0; i<4; i++){
                int nextx = row+x[i];
                int nexty = col+y[i];
                if(!(nextx<0)&&!(nextx>=grid.length)&&!(nexty<0)&&!(nexty>=grid[0].length))
                {
                    if(grid[nextx][nexty]==1 && visited[nextx][nexty]==0){
                        queue.offer(new int[]{nextx,nexty,dis+1});
                        visited[nextx][nexty]=1;
                    }
                }

            }

        }

        return distance;

    }
}
