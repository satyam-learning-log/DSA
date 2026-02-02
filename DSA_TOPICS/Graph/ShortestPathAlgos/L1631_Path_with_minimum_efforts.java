package ShortestPathAlgos;


import java.util.*;

public class L1631_Path_with_minimum_efforts {

    public int minimumEffortPath(int[][] heights) {

    if (heights.length == 1 && heights[0].length == 1)
        return 0;

    Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    int minCost[][] = new int[heights.length][heights[0].length];

    for (int minc[] : minCost)
        Arrays.fill(minc, Integer.MAX_VALUE);

    minCost[0][0] = 0;

    queue.add(new int[] { 0, 0, 0 });

    while (!queue.isEmpty()) {

        int current[] = queue.poll();
        int maxeffort = current[0];
        int currrow = current[1];
        int currcol = current[2];

        if(maxeffort>minCost[currrow][currcol]) continue;

        if (currrow == heights.length - 1 && currcol == heights[0].length-1)
            return minCost[currrow][currcol];

        int x[] = { -1, 0, 1, 0 };
        int y[] = { 0, -1, 0, 1 };

        // checking four of the directions
        for (int i = 0; i < 4; i++) {

            int nx = x[i] + currrow;
            int ny = y[i] + currcol;

            if (nx < 0 || nx >= heights.length || ny < 0 || ny >= heights[0].length)
                continue;

            int curreffort = Math.abs(heights[nx][ny] - heights[currrow][currcol]);

            int curr_maxeffort = Math.max(curreffort, maxeffort);

            if (curr_maxeffort < minCost[nx][ny]) {
                minCost[nx][ny] = curr_maxeffort;
                queue.add(new int[] { curr_maxeffort, nx, ny });
            }

        }

    }

    return -1;

}
}
