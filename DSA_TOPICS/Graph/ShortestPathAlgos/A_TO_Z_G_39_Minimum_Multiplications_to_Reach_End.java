package ShortestPathAlgos;

import java.lang.reflect.Array;
import java.util.*;

public class A_TO_Z_G_39_Minimum_Multiplications_to_Reach_End {

    public static void main(String[] args){

        int start = 7;
        int end =  66175;

//        int adjancent[] = new int[]{2, 5, 7};
        int adjancent[] = new int[]{3, 4, 65};


        int distance[] = new int[100001];

        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start]=0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        if(end<100000){
            pq.add(new int[]{0,start});
        }

        boolean reached = false;

        while(!pq.isEmpty()){

            int curr[] = pq.poll();

            int currentdistance = curr[0];
            int current = curr[1];

            if(distance[current]<currentdistance) continue;

            if(current==end){
                reached = true;
                System.out.println("Required steps :- "+currentdistance);
                break;
            }

            for(int i=0; i<adjancent.length; i++){
                int next = (current*adjancent[i])%100000;
                if(distance[next]>currentdistance+1){
                    distance[next] = currentdistance+1;
                    pq.add(new int[]{currentdistance+1,next});
                }
            }

        }

        if(!reached){
            System.out.println("Can't reach");
        }

    }
}
