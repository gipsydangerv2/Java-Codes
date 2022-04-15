package code.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortExample {

    // This topological sort is performed using the DFS.

    static class Graph {
        int vertices;
        boolean isDirectedGraph;
        List<List<Integer>> adjacencyList;

        public Graph(int vertices, boolean isDirectedGraph) {
            this.vertices = vertices;
            this.isDirectedGraph = isDirectedGraph;
            adjacencyList = new ArrayList<>();

            for(int idx = 0; idx <= vertices; idx ++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest) {
            adjacencyList.get(src).add(dest);

            if (!isDirectedGraph) {
                adjacencyList.get(dest).add(src);
            }
        }

        public List<Integer> performTopologicalSort() {
            // first check if cycle is present in the graph or not.
            // if cycle is not present in the graph, then only run the topological sort.
            // topological sort runs on DAG (Directed Acyclic Graph).

            boolean[] visited = new boolean[vertices + 1];
            Stack<Integer> stack = new Stack<>();

            for(int idx = 1; idx <= vertices; idx ++) {
                if (!visited[idx]) {
                    dfs(idx, visited, stack);
                }
            }

            List<Integer> ordering = new ArrayList<>();
            while(!stack.isEmpty()) {
                ordering.add(stack.pop());
            }

            return ordering;
        }

        private void dfs(int vertex, boolean[] visited, Stack<Integer> stack) {
            visited[vertex] = true;

            for(Integer adjacent : adjacencyList.get(vertex)) {
                if (!visited[adjacent]) {
                    dfs(adjacent, visited, stack);
                }
            }
            stack.push(vertex);
        }
    }
    public static void main(String[] args) {
        int vertices = 7;
        Graph graph = new Graph(vertices, true);

        graph.addEdge(5, 1);
        graph.addEdge(6, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(5, 2);
        graph.addEdge(6, 3);

        System.out.println(graph.performTopologicalSort());
    }
}
