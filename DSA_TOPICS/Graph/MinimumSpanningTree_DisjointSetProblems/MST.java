package MinimumSpanningTree_DisjointSetProblems;

import java.util.*;

public class MST {

    static List<List<int[]>> adjacencyList = new ArrayList<>();
    static List<int[]> pairOfMST = new ArrayList<>();

    public static void buildAdjacencyList(int edges[][]){

        for(int i=0; i<5; i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            adjacencyList.get(edges[i][0]).add(new int[]{edges[i][1],edges[i][2]});
            adjacencyList.get(edges[i][1]).add(new int[]{edges[i][0],edges[i][2]});
        }
    }
    public  static int findMSTAndReturnTheSum(int visited[],int start){

        Queue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);


        int sum=0;

        pq.add(new int[]{0,start,-1});


        while(!pq.isEmpty()){

            int [] pair = pq.poll();

            if(visited[pair[1]]==1) continue;

            sum+=pair[0];
            visited[pair[1]]=1;
            pairOfMST.add(new int[]{pair[1],pair[2]});

            List<int[]> neighbors = adjacencyList.get(pair[1]);

            for(int i=0; i<neighbors.size(); i++){
                int neighbor[] = neighbors.get(i);
                if(visited[neighbor[0]]==0){
                    // w, v , u
                    pq.add(new int[]{neighbor[1],neighbor[0],pair[1]});
                }
            }

        }

        return sum;
    }
    public static void main(String[] args){
        // u <=> v , weight
        int edges [][] = {{0,1,2},{2,0,1},{2,1,1},{2,4,2},{3,4,1},{2,3,2}};
        int visited [] = new int[5];
        int start = 0;
        buildAdjacencyList(edges);

        int sumOfMST = findMSTAndReturnTheSum(visited,start);

        System.out.println(sumOfMST);

        for(int [] pair : pairOfMST ){
            System.out.println(pair[0]+"->"+pair[1]);
        }
    }
}
