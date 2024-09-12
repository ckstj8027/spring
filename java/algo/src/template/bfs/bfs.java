package template.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class bfs {
    static HashMap<Integer, Boolean> visited = new HashMap<>();

    public static void bfs(Map<Integer, List<Integer>> graph, int startVertex) {
        if (graph == null || !graph.containsKey(startVertex)) {
            System.out.println("Invalid graph or start vertex.");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        visited.put(startVertex, true);

        while (!queue.isEmpty()) {
            int curVertex = queue.poll();
            List<Integer> neighbors = graph.get(curVertex);
            if (neighbors != null) {
                for (int nextVertex : neighbors) {
                    if (!visited.containsKey(nextVertex)) {
                        queue.offer(nextVertex);
                        visited.put(nextVertex, true);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        // 예제 그래프 생성
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> neighborsOf0 = Arrays.asList(1, 3,6);
        List<Integer> neighborsOf1 = Arrays.asList(0,3);
        List<Integer> neighborsOf2 = Arrays.asList(3);
        List<Integer> neighborsOf3 = Arrays.asList(0,1,2,7);
        List<Integer> neighborsOf4 = Arrays.asList(5);
        List<Integer> neighborsOf5 = Arrays.asList(4,6,7);
        List<Integer> neighborsOf6 = Arrays.asList(0,5);
        List<Integer> neighborsOf7 = Arrays.asList(3,5);
        graph.put(0, neighborsOf0);
        graph.put(1, neighborsOf1);
        graph.put(2, neighborsOf2);
        graph.put(3, neighborsOf3);
        graph.put(4, neighborsOf4);
        graph.put(5, neighborsOf5);
        graph.put(6, neighborsOf6);
        graph.put(7, neighborsOf7);

        System.out.println(graph);


        // bfs 메소드 실행
        bfs(graph, 0);


        // 결과 출력
        System.out.println("Visited nodes: " + visited.keySet());
    }
}