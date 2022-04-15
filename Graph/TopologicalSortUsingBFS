package code.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortUsingBFS {
    // this topological sort is performed using BFS. It is also know as Kahn's Algorithm.

    static class Graph {
        int vertices;
        List<List<Integer>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();

            for (int idx = 0; idx <= vertices; idx++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest) {
            adjacencyList.get(src).add(dest);
        }

        public List<Integer> topologicalSort() {
            List<Integer> result = new ArrayList<>();

            // STEP 1: Calculate the indegree of the nodes.

            int[] indegree = new int[vertices + 1];

            for (int idx = 1; idx <= vertices; idx++) {
                for (Integer adjacent : adjacencyList.get(idx)) {
                    indegree[adjacent]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int idx = 1; idx <= vertices; idx++) {
                if (indegree[idx] == 0) {
                    queue.add(idx);
                }
            }

            int count = 0; // to check if cycle exist or not.

            // STEP 2: Process the queue. or perform the BFS.
            while (!queue.isEmpty()) {

                Integer node = queue.remove();
                result.add(node);
                count++;

                for (Integer adjacent : adjacencyList.get(node)) {
                    indegree[adjacent]--;

                    if (indegree[adjacent] == 0) {
                        queue.add(adjacent);
                    }
                }
            }

            if (count != vertices) {
                System.out.println("The graph contains a cycle !!!.");
            }

            // STEP 3: Return the answer.
            return result;

        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        Graph graph = new Graph(7);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        graph.addEdge(3, 2);
        graph.addEdge(3, 7);

        graph.addEdge(4, 5);

        graph.addEdge(5, 6);
        graph.addEdge(7, 4);
        graph.addEdge(7, 6);

        System.out.println(graph.topologicalSort());
    }
}
