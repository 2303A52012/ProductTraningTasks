import java.util.*;
public class Solution1 {

    public static void bfs(HashMap<Integer, ArrayList<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int v= scan.nextInt();
        int e =scan.nextInt();
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int i=0;i<e;i++){
            int u = scan.nextInt();
            int v1 = scan.nextInt();
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v1);
            graph.putIfAbsent(v1, new ArrayList<>());
            graph.get(v1).add(u);
        }

        bfs(graph, 0);

    }
}
