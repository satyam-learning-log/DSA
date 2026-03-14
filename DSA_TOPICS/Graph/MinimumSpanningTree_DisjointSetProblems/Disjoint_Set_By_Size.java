package MinimumSpanningTree_DisjointSetProblems;


import java.util.*;

public class Disjoint_Set_By_Size {

    int [] size;
    int [] parent;

    Disjoint_Set_By_Size(int no_of_nodes){

        size = new int[no_of_nodes+1];
        parent = new int[no_of_nodes+1];
        Arrays.fill(size,1);
        for(int i=0; i<no_of_nodes; i++) parent[i] = i;

    }

    public int findUltimateParent(int node){

        if(node == parent[node]){
            return node;
        }

        parent[node] = findUltimateParent(parent[node]);
        return parent[node];
    }

    public void unionBySize(int u , int v){

        // check ultimate parent of u and v
        int upu = findUltimateParent(u);
        int upv = findUltimateParent(v);

        // if both belong to same parent
        if(upu==upv) return;

        if(size[upu]<size[upv]){
            parent[upu] = upv;
            size[upv]+=size[upu];
        }else{
            parent[upv] = upu;
            size[upu]+=size[upv];
        }

    }

    public static void main(String args[]){
        Disjoint_Set_By_Size ds = new Disjoint_Set_By_Size(7);
        ds.unionBySize(1 , 2);
        ds.unionBySize(5,6);
        ds.unionBySize(2,3);
        //check if 1 amd 4 belongs to same parent;

        System.out.println(ds.findUltimateParent(1)==ds.findUltimateParent(4));
        System.out.println(ds.size[1]);
        ds.unionBySize(3,4);

        System.out.println(ds.findUltimateParent(1)==ds.findUltimateParent(4));
    }



}
