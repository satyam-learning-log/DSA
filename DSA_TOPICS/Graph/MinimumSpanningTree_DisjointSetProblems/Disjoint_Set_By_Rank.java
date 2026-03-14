package MinimumSpanningTree_DisjointSetProblems;

// it helps us to parent and if both nodes belongs to same component in 0(1) time.
public class Disjoint_Set_By_Rank {

     int [] rank;
     int [] parent;

     Disjoint_Set_By_Rank(int no_of_nodes){

         rank = new int[no_of_nodes+1];
         parent = new int[no_of_nodes+1];

         for(int i=0; i<no_of_nodes; i++) parent[i] = i;

     }

     public int findUltimateParent(int node){

         if(node == parent[node]){
             return node;
         }

         parent[node] = findUltimateParent(parent[node]);
         return parent[node];
     }

     public void unionByRank(int u , int v){

         // check ultimate parent of u and v
         int upu = findUltimateParent(u);
         int upv = findUltimateParent(v);

         // if both belong to same parent
         if(upu==upv) return;

         if(rank[upu]<rank[upv]){
             parent[upu] = upv;
         }else if(rank[upu]>rank[upv]){
             parent[upv] = upu;
         }else{
             parent[upv] = upu;
             rank[upv]++;
         }

     }

     public static void main(String args[]){
     Disjoint_Set_By_Rank ds = new Disjoint_Set_By_Rank(7);
     ds.unionByRank(1 , 2);
     ds.unionByRank(5,6);
     ds.unionByRank(2,3);
     //check if 1 amd 4 belongs to same parent;

     System.out.println(ds.findUltimateParent(1)==ds.findUltimateParent(4));

     ds.unionByRank(3,4);

     System.out.println(ds.findUltimateParent(1)==ds.findUltimateParent(4));
     }
}
