import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int[][] rooms1;
    static int[][] rooms2;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

       // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       // StringTokenizer st = new StringTokenizer(br.readLine());

       // int n = Integer.parseInt(st.nextToken());

        rooms2=new int[][]{{1},{2},{3},{}};
        rooms1=new int[][]{{1,3},{3,0,1},{2},{0}};


        visited=new boolean[rooms2.length];
        visited[0]=true;

        bfs(0);

        Map<Boolean,Integer> dic=new HashMap<>();

        for (int i=0;i<rooms2.length;i++){
           // System.out.println(visited[i]);
            dic.put(visited[i],i);

        }
        if(dic.containsKey(false)){
            System.out.println(false);
        }
        else {
            System.out.println(true);
        }



    }
    public static void bfs(int n){
        Deque<int[]> q=new ArrayDeque<>();
        q.offer(rooms2[0]);


        while (!q.isEmpty()){
            int[] cur=q.poll();

            for(int cur_v :cur){
                if(visited[cur_v]==false){
                    q.offer(rooms2[cur_v]);
                    visited[cur_v]=true;
                }
            }

        }
    }




}


