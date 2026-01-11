package ProblemsOnBFSAndDFS;

public class L733_Flood_Fill {
    
    public void dfs(int sr , int sc , int [][] image , int color , int orgcolor ){

        if(sr<0 || sr>=image.length || sc<0 || sc>=image[0].length) return;
        if(image[sr][sc]!=orgcolor) return;

        //set new color
        image[sr][sc] = color;

        // explore its adjacents
        int srx []= new int[]{-1,0,1,0};
        int scy []= new int[]{0,-1,0,1};

        for(int i=0; i<4; i++){
            dfs(sr+srx[i],sc+scy[i],image,color,orgcolor);
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        //DFS approch
        int orgColor =  image[sr][sc];
        // no need to update
        if(orgColor==color) return image;
        dfs(sr, sc , image ,  color , orgColor);
        return image;

       /*
       // BFS approch

        int orgcolor = image[sr][sc];
        if(orgcolor==color) return image;

        int orgcolorCount = 0;

        image[sr][sc] = color;

        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[0].length; j++){
                if(image[i][j]==orgcolor){
                    orgcolorCount++;
                }
            }
        }

        if(orgcolorCount==0) return image;

        Queue<int[]> queue =  new LinkedList<>();

        queue.add(new int[]{sr,sc});

        while(!queue.isEmpty()){

        int size = queue.size();

        for(int i=0; i<size; i++){

            int current[] = queue.poll();
            int row = current[0];
            int col = current[1];

            //up
            if(row>0&&image[row-1][col]==orgcolor){
                queue.add(new int[]{row-1,col});
                image[row-1][col]=color;
            }

            //down
            if(row<image.length-1&&image[row+1][col]==orgcolor){
                queue.add(new int[]{row+1,col});
                image[row+1][col]=color;
            }

            //right
            if(col<image[0].length-1&&image[row][col+1]==orgcolor){
                 queue.add(new int[]{row,col+1});
                image[row][col+1]=color;
            }

            //left
            if(col>0&&image[row][col-1]==orgcolor){
                 queue.add(new int[]{row,col-1});
                image[row][col-1]=color;
            }

        }


        }

         return image;

         */


    }
}
