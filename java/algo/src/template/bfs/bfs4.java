package template.bfs;

import java.util.*;

public class bfs4 {
    static List<Integer> visitedOrder=new ArrayList<>();
    static Map<Integer,Boolean> visited=new HashMap<>();

    public static void main(String[] args) {
        HashMap<Integer,List<Integer>> graph =new HashMap<>();


        List<Integer> neighborsOf0 = Arrays.asList(1, 3,6);
        List<Integer> neighborsOf1 = Arrays.asList(0,3);
        List<Integer> neighborsOf2 = Arrays.asList(3);
        List<Integer> neighborsOf3 = Arrays.asList(0,1,2,7);
        List<Integer> neighborsOf4 = Arrays.asList(5);
        List<Integer> neighborsOf5 = Arrays.asList(4,6,7);
        List<Integer> neighborsOf6 = Arrays.asList(0,5);
        List<Integer> neighborsOf7 = Arrays.asList(3,5);

        graph.put(0,neighborsOf0);
        graph.put(1, neighborsOf1);
        graph.put(2, neighborsOf2);
        graph.put(3, neighborsOf3);
        graph.put(4, neighborsOf4);
        graph.put(5, neighborsOf5);
        graph.put(6, neighborsOf6);
        graph.put(7, neighborsOf7);

        bfs(graph,0);


        System.out.println(visited);
        System.out.println("최종답은");
        System.out.println(visitedOrder);

    }

    public static void bfs(Map<Integer,List<Integer>> graph, int startVertex){

        //static List<Integer> visitedOrder=new ArrayList<>();
        //static Map<Integer,Boolean> visited=new HashMap<>();

        Queue<Integer> q=new LinkedList<>();
        q.offer(startVertex);
        visitedOrder.add(startVertex);
        visited.put(startVertex,true);
        while (!q.isEmpty()){
            int curVertex=q.poll();



            List<Integer> nexts=graph.get(curVertex);


            for (int i=0;i<nexts.size();i++){
                if(!visited.containsKey(nexts.get(i))  ) {
                    System.out.println(nexts.get(i)+ "일단 딕셔너리에없으며 ");

                    if(!visitedOrder.contains(nexts.get(i))){
                        System.out.println(nexts.get(i)+ " 순서리스트에 없으니 추가 ");
                        q.offer(nexts.get(i));
                        visitedOrder.add(nexts.get(i));

                        visited.put(nexts.get(i),true);

                    }


                }


            }


        }



    }
}
