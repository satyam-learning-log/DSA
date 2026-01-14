package ProblemsOnBFSAndDFS;

public class L130_Surrounded_Regions_DFS {
    public void DFS(int row , int col , char board[][]){

        if(row<0 || row >=board.length || col<0 || col>= board[0].length) return;

        if(board[row][col]=='X' || board[row][col]=='#') return;

        board[row][col]='#';

        int nextrow[] = new int[]{-1,0,1,0};
        int nextcol[] = new int[]{0,-1,0,1};

        for(int i=0; i<4; i++){
            DFS(row+nextrow[i],col+nextcol[i],board);
        }

    }

    public void solve(char[][] board) {
        //it will be on the edge
        if(!(board.length>2)||!(board[0].length>2)) return;

        //traverse boundries and start dfs from 0 cells and mark its adjacents as not convertable

        for(int i=0; i<board.length; i++){
            DFS(i,0,board);
            DFS(i,board[0].length-1,board);
        }

        for(int j=0; j<board[0].length; j++){
            DFS(0,j,board);
            DFS(board.length-1,j,board);
        }

        // mark nonconvertable as 0 and convertable as X
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j]=='#'){
                    board[i][j]='O';
                }else if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }

        return ;
    }
}
