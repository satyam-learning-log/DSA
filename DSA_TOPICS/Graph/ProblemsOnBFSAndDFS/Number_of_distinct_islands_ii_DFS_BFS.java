package ProblemsOnBFSAndDFS;

public class Number_of_distinct_islands_ii_DFS_BFS {

    public static void DFS(int row,int col,int grid[][], int visited[][]){

        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length || visited[row][col]==0){
            return;
        }

        grid[row][col]=0;
        visited[row][col]=0;

        int nextx[] = {-1,-1,0,1,1,1,0,-1};
        int nexty[] = {0,-1,-1,-1,0,1,1,1};

        for(int i=0; i<8; i++){
            DFS(row+nextx[i],col+nexty[i],grid,visited);
        }

    }

    public  static void findIslands(int grid[][]){

        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        int visited [][] = new int[grid.length][grid[0].length];


        for(int i=0; i<n; i++){
            System.arraycopy(grid[i], 0, visited[i], 0, m);
        }

        for(int i=0; i<n; i++){

            for(int j=0; j<m; j++){

                if(grid[i][j]==1 && visited[i][j]==1){
                    DFS(i,j,grid,visited);
                    count++;
                }

            }

        }


        System.out.println("Number of islands are - "+count);


    }

    public static void main(String[] args){
        int grid[][] = {{0,1,1,0},{0,1,1,0},{0,0,0,0},{1,1,0,0}};
        findIslands(grid);
    }

}

