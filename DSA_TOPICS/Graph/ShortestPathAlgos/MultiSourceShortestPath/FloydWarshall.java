package ShortestPathAlgos.MultiSourceShortestPath;

public class FloydWarshall {

    public static void buildAdjacencyMatrix(int nodes , int edges[][] , int adjMatrix[][]){
        for(int i=0; i<edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            int cost = edges[i][2];
            adjMatrix[from][to]=cost;
        }
    }

    public static void main(String [] args){

        int nodes = 5;
        int edges[][] = {{0,1,6},{0,2,2},{0,3,4},{3,1,1},{2,1,3},{2,4,1},{4,1,1}};
        int adjMatrix[][] = new int[5][5];

        for(int i=0; i<nodes; i++){
            for(int j=0; j<nodes; j++){
                if(i!=j)
                    adjMatrix[i][j]=Integer.MAX_VALUE;
            }
        }
        buildAdjacencyMatrix(nodes,edges,adjMatrix);

        for(int k=0; k<nodes; k++){
            for(int i=0; i<nodes; i++){
                for(int j=0; j<nodes; j++){
                    if(adjMatrix[i][k]!=Integer.MAX_VALUE && adjMatrix[k][j]!=Integer.MAX_VALUE)
                    adjMatrix[i][j] = Math.min(adjMatrix[i][k]+adjMatrix[k][j],adjMatrix[i][j]);
                }
            }
        }

        for(int i=0; i<nodes; i++){
            for(int j=0; j<nodes; j++){
                System.out.println("Cost from "+i+" to "+j+" is "+ adjMatrix[i][j]);
            }
        }

    }
}
