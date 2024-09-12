package template.dfs;



import java.util.*;

public class dfs1{
    static  List<Integer>   visited=new ArrayList<>();

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

        // DFS 실행
        dfs(graph, 0);

        // 결과 출력
        System.out.println("Visited nodes: " + visited);
    }


    public static void dfs(Map<Integer, List<Integer>> graph, int curVertex) {
        visited.add(curVertex);
        for (int nextVertex : graph.get(curVertex)) {
            if (!visited.contains(nextVertex)) {
                dfs(graph, nextVertex);
            }
        }
    }
}
