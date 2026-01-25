package Directed_Graph;

import java.util.*;

public class ATOZ_G_26_Alien_dictionary {


    
   public static boolean createAdjacencyList(int N , int K , int [] indgree , String[] dict , List<List<Integer>> adjacencyList){

       for(int i=0; i<K; i++){
           adjacencyList.add(new ArrayList<>());
       }

       for(int i=0; i<N-1; i++){

           String s1 = dict[i];
           String s2 = dict[i+1];

           //check if dictionary is valid
           // s1 = "baab" s2 = "baa" -> invalid dictionary
           // in question its stated that dictonary would be in sorted order

           if(s1.length()>s2.length()&&s1.startsWith(s2)) return false;

           int minLength = Math.min(s1.length(),s2.length());

           for(int si=0; si<minLength; si++){
              if(s1.charAt(si)!=s2.charAt(si)) {
                  adjacencyList.get(s1.charAt(si) - 'a').add(s2.charAt(si) - 'a');
                  indgree[s2.charAt(si)-'a']++;
                  break;
              }
           }

       }

       return true;
   }

    public static void main(String[] args){

        //Valid
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        //Invalid
//        int N = 5, K = 4;
//        String[] dict = {"baad", "baa", "abca", "cab", "cad"};

        int [] indgree = new int[K];
        //create adjacency list;
        List<List<Integer>> adjacencyList = new ArrayList<>();

        if(!createAdjacencyList(N,K,indgree,dict,adjacencyList)){
            System.out.println("Invalid dictionary");
        }else {

            Queue<Integer> queue = new LinkedList();

            StringBuffer sbf = new StringBuffer();

            for (int i=0; i<indgree.length; i++) {
                if (indgree[i] == 0)
                    queue.offer(i);
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                List<Integer> neighbors = adjacencyList.get(current);

                for (int i = 0; i<neighbors.size(); i++) {
                    indgree[neighbors.get(i)]--;
                    if (indgree[neighbors.get(i)] == 0)
                        queue.add(neighbors.get(i));
                }

                sbf.append((char)(current+97));
            }


            System.out.println(sbf);
        }

    }
}
