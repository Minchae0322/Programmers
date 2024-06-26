import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] value;

    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int V = Integer.parseInt(input[2]);

        value = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            value[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);

            value[start].add(end);
            value[end].add(start);
        }
        dfs(V);
        System.out.println();
        isVisited = new boolean[N + 1];
        bfs(V);
    }

    public static void dfs(int node) {
        isVisited[node] = true;
        System.out.print(node + " ");
        Collections.sort(value[node]);

        for (int connection : value[node]) {
            if (isVisited[connection]) {
                continue;
            }
            dfs(connection);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        isVisited[start] = true;
        List<Integer> result = new ArrayList<>();
        result.add(start);

        while (!que.isEmpty()) {
            int now = que.poll();
            Collections.sort(value[now]);
            for (int connection : value[now]) {
                if (isVisited[connection]) {
                    continue;
                }

                isVisited[connection] = true;
                result.add(connection);
                que.add(connection);
            }
        }

        for (int n : result) {
            System.out.print(n + " ");
        }
        System.out.println();

    }
}