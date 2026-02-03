package ShortestPathAlgos;


import java.util.*;

public class L787_Cheapest_Flights_Within_K_Stops {

        public void createAdjacencyList(
                List<List<int[]>> adjacencyList,
                int n,
                int[][] flights
        ) {

            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            for (int i = 0; i < flights.length; i++) {

                int[] edge = flights[i];

                int from = edge[0];
                int to   = edge[1];
                int cost = edge[2];

                adjacencyList.get(from).add(new int[] { to, cost });
            }
        }

        public int findCheapestPrice(
                int n,
                int[][] flights,
                int src,
                int dst,
                int k
        ) {

            Queue<int[]> pq = new LinkedList<>();
            List<List<int[]>> adjacencyList = new ArrayList<>();

            int[] cost = new int[n];
            Arrays.fill(cost, Integer.MAX_VALUE);

            createAdjacencyList(adjacencyList, n, flights);

            // { totalCost, node, stops }
            pq.add(new int[] { 0, src, 0 });

            while (!pq.isEmpty()) {

                int size = pq.size();

                for (int j = 0; j < size; j++) {

                    int[] currentEntry = pq.poll();
                    int currCost  = currentEntry[0];
                    int current   = currentEntry[1];
                    int currLevel = currentEntry[2];

                    if (currLevel > k) {
                        break;
                    }

                    List<int[]> neighbors = adjacencyList.get(current);

                    for (int i = 0; i < neighbors.size(); i++) {

                        int[] neighborDetails = neighbors.get(i);

                        int neighbor     = neighborDetails[0];
                        int costToVisit  = neighborDetails[1];

                        if (currCost + costToVisit < cost[neighbor]) {
                            cost[neighbor] = currCost + costToVisit;
                            pq.add(new int[] {
                                    cost[neighbor],
                                    neighbor,
                                    currLevel + 1
                            });
                        }
                    }
                }
            }

            return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
        }

}
