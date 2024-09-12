package dijkstra.networkDelayTime;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.lang.Math;

class Solution {

    //int[][] times = {{2,1,2},{2,3,5},{2,4,1},{4,3,3}};
    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<Pair>> graph = new ArrayList<>();
        for(int i = 0;i<n+1;i++){
            graph.add(new ArrayList<Pair>());
        }
        System.out.println(graph);
        for (int[] time: times){
            int u = time[0];
            int v = time[1];
            int w = time[2];
            System.out.println(graph.get(u));
            graph.get(u).add(new Pair(w, v));
            System.out.println(graph.get(u));
        }
        System.out.println(graph);
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k] = 0;
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>((p1,p2)->Integer.compare(p1.left,p2.left));
        priorityQueue.offer(new Pair(0, k));
        while (!priorityQueue.isEmpty()) {
            Pair<Integer, Integer> curPair = priorityQueue.poll();
            int u = curPair.getRight();//k
            for (Pair<Integer, Integer> p : graph.get(u)){
                int w = p.getLeft();
                int v = p.getRight();
                if (dis[u] + w < dis[v]){
                    priorityQueue.offer(new Pair(dis[u] + w, v));
                    dis[v] = dis[u] + w;
                }
            }
        }
        int answer = 0;
        for(int i = 1; i <= n; i++){
            if(dis[i] == Integer.MAX_VALUE){
                return -1;
            }
            answer = Math.max(answer, dis[i]);
        }
        return answer;
    }

    class Pair<L, R> {
        L left;
        R right;

        private Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left.toString() + ", " + right.toString() + ")";
        }

        public L getLeft() {
            return this.left;
        }

        public R getRight() {
            return this.right;
        }
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,2},{2,3,5},{2,4,1},{4,3,3}};
        int n = 4;
        int k = 2;

        Solution solution = new Solution();
        int delayTime = solution.networkDelayTime(times, n, k);
        System.out.println("Network Delay Time: " + delayTime);
    }
}
