package ProblemsOnBFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

public class L994_Rotting_Oranges {
    public int orangesRotting(int[][] grid) {

        //expected
        //if  rottencount > 0 - check its 4-directionally adjacent , try to make all oranges rotten and return min minutes required
        //if  freshcount == 0 ( //if rottencount && freshcount  return 0; ) return 0 as minrequired
        // if rottencount == 0 return -1


        //What is aim ? to make all oranges rotten

        // why bfs - every minute it's "adjacent" becomes rotten
        // as its saying we need to visit adjacent will use bfs here

        //why we are adding all rotten in the queue bcoz
        // 2 1 0
        // 1 1 0
        // 0 1 0
        // 0 1 0
        // 0 2 0

        // at every min. every rotten orange is helping us to make all fresh -> rotten
        // if we apply bfs on each rotten . we can't count min. mintues required to make all rotten


        // approch

        // initially - all rotten will be in queue

        // at each step will add its 4-directionally adjacent where grid[r][c]=1 in queue
        // which will become rotten in next step

        // will decrement the freshcount -- and set grid [r][c] = 2 to ensure it should not be counted extra

        // will increment minRequired++ after processing current rotten oranges;

        // will repeat these steps until we traverse all rotten oranges

        // at the end if freshcount == 0 return minRequired




        int rottencount = 0;
        int freshcount = 0;

        Queue<int[]> rottenQueue = new LinkedList<>();

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==2){
                    rottencount++;
                    rottenQueue.offer(new int[]{i,j});
                }else if(grid[i][j]==1){
                    freshcount++;
                }
            }
        }

        if(freshcount==0) return 0;
        if(rottencount==0) return -1;

        int requiredMin = 0;

        while(!rottenQueue.isEmpty()){

            int size = rottenQueue.size();

            for(int i=0; i<size; i++){

                int [] currentRotten = rottenQueue.poll();
                int row = currentRotten[0];
                int col = currentRotten[1];

                //up
                if(row>0 && grid[row-1][col] == 1){
                    rottenQueue.offer(new int[]{row-1,col});
                    freshcount--;
                    grid[row-1][col] = 2;
                }
                //down
                if(row<grid.length-1 && grid[row+1][col] == 1){
                    rottenQueue.offer(new int[]{row+1,col});
                    freshcount--;
                    grid[row+1][col] = 2;
                }
                //right
                if(col<grid[0].length-1 && grid[row][col+1] == 1){
                    rottenQueue.offer(new int[]{row,col+1});
                    freshcount--;
                    grid[row][col+1] = 2;
                }
                //left
                if(col>0 && grid[row][col-1] == 1){
                    rottenQueue.offer(new int[]{row,col-1});
                    freshcount--;
                    grid[row][col-1] = 2;
                }

            }

            requiredMin++;

        }


        return (freshcount==0) ? requiredMin-1 : -1;

    }
}
