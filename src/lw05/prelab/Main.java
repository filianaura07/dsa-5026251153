package lw05.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static int[][] graph;
    static int size;
    static String[] names;

    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0 && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return order;
    }

    static boolean[] dfsVisited;
    static List<Integer> dfsOrder;

    static void dfs(int current) {
        dfsVisited[current] = true;
        dfsOrder.add(current);

        for (int next = 0; next < size; next++) {
            if (graph[current][next] > 0 && !dfsVisited[next]) {
                dfs(next);
            }
        }
    }

    static void dijkstraAlg(int start, int dest) {
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(visited, dist);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0 && !visited[next]) {
                    int newDist = dist[current] + graph[current][next];

                    if (newDist < dist[next]) {
                        dist[next] = newDist;
                        prev[next] = current;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");

        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println("Route not found");
        } else {
            route(dest, start, prev);
            System.out.println();
            System.out.println("Total time: " + dist[dest] + " min");
        }
    }

    static int findTheNextNode(boolean[] visited, int[] dist) {
        int min = Integer.MAX_VALUE;
        int closest = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                closest = i;
            }
        }

        return closest;
    }

    static void route(int current, int start, int[] prev) {
        if (current == start) {
            System.out.print(names[current]);
        } else {
            route(prev[current], start, prev);
            System.out.print(" -> " + names[current]);
        }
    }

    static void printPath(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(names[list.get(i)]);

            if (i < list.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        File inputFile = new File("src/lw05/prelab/campus.txt");

        if (!inputFile.exists()) {
            inputFile = new File("campus.txt");
        }

        try {
            Scanner sc = new Scanner(inputFile);

            size = sc.nextInt();
            names = new String[size];

            for (int i = 0; i < size; i++) {
                names[i] = sc.next();
            }

            graph = new int[size][size];

            int e = sc.nextInt();

            for (int i = 0; i < e; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int minutes = sc.nextInt();

                addEdge(v1, v2, minutes);
            }

            int start = sc.nextInt();
            int dest = sc.nextInt();

            sc.close();

            System.out.println("=== BFS from " + names[start] + " ===");
            printPath(bfs(start));

            System.out.println("=== DFS from " + names[start] + " ===");

            dfsVisited = new boolean[size];
            dfsOrder = new ArrayList<>();

            dfs(start);
            printPath(dfsOrder);

            dijkstraAlg(start, dest);

        } catch (FileNotFoundException e) {
            System.out.println("File campus.txt tidak ditemukan.");
            System.out.println("Pastikan campus.txt berada di folder src/lw05/prelab.");
        }
    }
}
