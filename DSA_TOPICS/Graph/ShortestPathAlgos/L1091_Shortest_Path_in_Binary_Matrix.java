package ShortestPathAlgos;

import java.util.*;

public class L1091_Shortest_Path_in_Binary_Matrix {
    public int shortestPathBinaryMatrix(int[][] grid) {

        //initial check
        if(grid[0][0] !=0 || grid[grid.length-1][grid[0].length-1] !=0) return -1;

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0,0});
        int level = 1;
        grid[0][0] = 1;

        while(!queue.isEmpty()){

            int size = queue.size();

            System.out.println(size);

            for(int j=0; j<size; j++){

                int [] current = queue.poll();

                int row = current[0];
                int col = current[1];

                if( row == grid.length-1 && col == grid[0].length-1) return level;

                //check 8 directions
                int [] x = {-1,-1,0,1,1,1,0,-1};
                int [] y = {0,-1,-1,-1,0,1,1,1};

                for(int i=0; i<8; i++){

                    int nextrow = row+x[i];
                    int nextcol = col+y[i];
                    //outof bound
                    if(row+x[i]<0 || row+x[i]>=grid.length || col+y[i]<0 || col+y[i]>=grid[0].length ) continue;

                    if(grid[row+x[i]][col+y[i]]!=1){
                        System.out.println(nextrow + "- " + nextcol);
                        grid[row+x[i]][col+y[i]] = 1;
                        queue.offer(new int[]{nextrow,nextcol});
                    }

                }

            }

            level++;

        }


        return -1;
    }
}
