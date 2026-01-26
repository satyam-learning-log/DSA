package ShortestPathAlgos;



import java.util.*;

public class Shortest_path_in_Directed_Acyclic_Graph {

    public static void createAdjacencyList(int N ,  int [][] edges ,int indgree[] , List<List<int[]>> adjacencyList){

        for(int i=0; i<N; i++){
             adjacencyList.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            int cost = edges[i][2];
            adjacencyList.get(from).add(new int[]{to,cost});
            indgree[to]++;
        }

    }
    public static Queue<Integer> toposort(int indgree[] , List<List<int[]>> adjacencyList){

        Queue<Integer> outputqueue = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<indgree.length; i++){
            if(indgree[i]==0) queue.add(i);
        }

        while(!queue.isEmpty()){
            //poll current element
            //iterate adjacent reduce indgree and add in the queue if indgree == 0
            int current = queue.poll();
            List<int[]> neighbors  = adjacencyList.get(current);

            for(int i=0; i<neighbors.size(); i++){
                int [] neighbor = neighbors.get(i);
                indgree[neighbor[0]]--;
                if(indgree[neighbor[0]]==0) queue.add(neighbor[0]);
            }
            outputqueue.offer(current);
        }

        return outputqueue;

    }
    public static void main(String [] args){

        int [][] edges = {{0,1,13},{0,2,4},{2,1,2},{3,0,5},{3,1,1}};
        int N = 4;

        int indgree[] = new int[N];
        List<List<int[]>> adjacencyList = new ArrayList<>();
        //fill adjacency list and indgree
       createAdjacencyList(N , edges , indgree,  adjacencyList);
       //topo sort know ordering
       Queue<Integer> toposortop = toposort( indgree ,  adjacencyList);


       int source = 0;

       int cost [] = new int[N];
       Arrays.fill(cost,Integer.MAX_VALUE);

       cost[source]=0;

       System.out.println("Can't reach");

       //Updated untraversible nodes from source
       while(toposortop.peek()!=source) {
           int current = toposortop.poll();
           cost[current]=-1;
           System.out.println(current);
       }

       System.out.println("Traverse starting from source and calculate the cost for each ");

       while(!toposortop.isEmpty()){
           //poll current element
           int current = toposortop.poll();

           //visit it's neighbors and update the cost

           List<int[]> neighbors  = adjacencyList.get(current);

           for(int i=0; i<neighbors.size(); i++){
               System.out.println("neighbor -> " + neighbors.get(i)[0] + "cost ->" + neighbors.get(i)[0]);
               int neighbor = neighbors.get(i)[0];
              int costtoreach =  neighbors.get(i)[1];

              //Update new cost if it's less than previous one
               cost[neighbor] =  Math.min(cost[neighbor],cost[current]+costtoreach);
               System.out.println("Updated cost ->"+cost[neighbor]);
           }


          for(int i=0; i<cost.length; i++){
              System.out.println("cost of :"+i+" is "+cost[i]);
          }

       }




    }
}
