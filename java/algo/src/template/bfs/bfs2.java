package template.bfs;
import java.net.Inet4Address;
import java.security.cert.TrustAnchor;
import java.util.*;

public class bfs2 {
    static List<Integer> visited = new ArrayList<>();
    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();



        List<Integer> neighborsOf0 = Arrays.asList(1, 3, 6);
        List<Integer> neighborsOf1 = Arrays.asList(0, 3);
        List<Integer> neighborsOf2 = Arrays.asList(3);
        List<Integer> neighborsOf3 = Arrays.asList(0, 1, 2, 7);
        List<Integer> neighborsOf4 = Arrays.asList(5);
        List<Integer> neighborsOf5 = Arrays.asList(4, 6, 7);
        List<Integer> neighborsOf6 = Arrays.asList(0, 5);
        List<Integer> neighborsOf7 = Arrays.asList(3, 5);
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
        System.out.println("Visited nodes: " + visited);
    }


    public static void bfs(Map<Integer, List<Integer>> graph, int startVertex) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startVertex);
        visited.add(startVertex);

        while (!q.isEmpty()){
            int curVertex=q.poll();
            //static List<Integer> visited = new ArrayList<>();
            List<Integer> nextVertexs=graph.get(curVertex);

            for (int nv :nextVertexs){

                if ( !visited.contains(nv)){
                    q.offer(nv);

                    visited.add(nv);
                }
            }



        }


    }


}

