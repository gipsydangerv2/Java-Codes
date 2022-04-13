package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;



class Result {
    static class Edge {
        int src, dest, wght;

        public Edge(int src, int dest, int wght) {
            this.src = src;
            this.dest = dest;
            this.wght = wght;
        }

        @Override
        public String toString() {
            return "Edge: [" + src + ", " + dest + ", " + wght + "]";
        }
    }

    static class Graph {
        int vertices;
        List<List<Edge>> adjacencyList;
        List<Set<Integer>> parent = new ArrayList<>();
        Set<String> allPaths = new HashSet<>();

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();

            for(int idx = 0; idx <= vertices; idx ++) {
                this.adjacencyList.add(new ArrayList<>());
                this.parent.add(new HashSet<>());
            }
        }

        public void addEdge(int src, int dest, int wght) {
            Edge edge = new Edge(src, dest, wght);
            adjacencyList.get(src).add(edge);

            adjacencyList.get(dest).add(new Edge(dest, src, wght));
        }

        public Set<String> solve() {
            // find the shortest path from the first node to the last node.

            int[] distance = new int[vertices + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);

            PriorityQueue<int[]> minHeap = new PriorityQueue<>((val1, val2) -> {
                return val1[0] - val2[0];
            });


            distance[1] = 0;
            minHeap.add(new int[] {0, 1});

            while(!minHeap.isEmpty()) {
                int[] minItem = minHeap.remove();

                int dist = minItem[0];
                int node = minItem[1];

                if (dist > distance[node]) {
                    continue;
                }

                for(Edge edge: adjacencyList.get(node)) {
                    if (distance[edge.dest] >= dist + edge.wght) {
                        if (distance[edge.dest] > dist + edge.wght) {
                            parent.get(edge.dest).clear();
                        }
                        distance[edge.dest] = dist + edge.wght;
                        parent.get(edge.dest).add(node);
                        minHeap.add(new int[] {dist + edge.wght, edge.dest});
                    }
                }
            }

            System.out.println(Arrays.toString(distance));
            System.out.println("Printing parent array: ");
            for(int idx = 1; idx <= vertices; idx ++) {
                System.out.println(idx + ": -->" + parent.get(idx));
            }

            findPaths(vertices, new HashMap<Integer, Boolean>());
            return allPaths;
        }

        public void findPaths(int dest, Map<Integer, Boolean> visited) {
            // for each dest, find all the paths coming to it.
            // then use the dynamic programming for the memoization.

            if (dest == 1 || visited.containsKey(dest)) {
                return;
            }
            visited.put(dest, true);

            for(Integer val : parent.get(dest)) {
                allPaths.add(val + "---" + dest);
                findPaths(val, visited);
            }
        }
    }

    public static List<String> classifyEdges(int gNodes, List<Integer> gFrom,
                                             List<Integer> gTo, List<Integer> gWeight) {

        int size = gFrom.size();
        Graph graph = new Graph(gNodes);
        List<String> result = new ArrayList<>();

        for(int idx = 0; idx < size; idx ++) {
            int src = gFrom.get(idx);
            int dest = gTo.get(idx);
            int wght = gWeight.get(idx);

            graph.addEdge(src, dest, wght);
        }

        Set<String> edgesList = graph.solve();

        for(int idx = 0; idx < size; idx ++) {
            int src = gFrom.get(idx);
            int dest = gTo.get(idx);
            int wght = gWeight.get(idx);

            if (edgesList.contains(src + "---" + dest) || edgesList.contains(dest + "---" + src)) {
                result.add("YES");
            } else {
                result.add("NO");
            }
        }

        return result;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader
                        .readLine()
                        .replaceAll("\\s+$", "")
                        .split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<String> result = Result.classifyEdges(gNodes, gFrom, gTo, gWeight);

        for(int idx = 0; idx < gFrom.size(); idx ++) {
            System.out.println("src : " + gFrom.get(idx) + ", dest: " + gTo.get(idx) +
                    ", wght: " + gWeight.get(idx) + ", included: " + result.get(idx));
        }

        //System.out.println(result);

        bufferedReader.close();
    }
}
