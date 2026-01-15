package ProblemsOnBFSAndDFS;

public class L1020_Number_Of_Enclaves {
    public void DFS(int row , int col , int[][] grid){

        //boundry cases
        if(row<0 || row >=grid.length || col<0 || col>= grid[0].length) return;

        // return if invalid / already visited cell
        if(grid[row][col]==0 || grid[row][col]==-1) return;

        //mark it as visited
        grid[row][col]=-1;

        int nextrow[] = new int[]{-1,0,1,0};
        int nextcol[] = new int[]{0,-1,0,1};

        for(int i=0; i<4; i++){
            DFS(row+nextrow[i],col+nextcol[i],grid);
        }

    }
    public int numEnclaves(int[][] grid) {
        // step1 - start dfs from each 1 present on the boundary
        // step2 - visit it's all adjacent cells which are one 1 and mark them as (-1) can walk out side.
        // step3 - after processing all 1's count remaining that will be our answer.

        if(!(grid.length>2)||!(grid[0].length>2)) return 0;

        for(int i=0; i<grid.length; i++){
            DFS(i,0,grid);
            DFS(i,grid[0].length-1,grid);
        }

        for(int j=0; j<grid[0].length; j++){
            DFS(0,j,grid);
            DFS(grid.length-1,j,grid);
        }

        int count=0;

        //count the cell via which we can't walk off
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==1){
                    count++;
                }
            }
        }

        return count;
    }
}
