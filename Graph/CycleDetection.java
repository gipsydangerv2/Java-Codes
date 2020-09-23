public class CycleDetection {
// This code is to detect cycle in a Directed Graph.
class Graph {
        private int vertices;
        private List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++)
                adjacencyList.add(new ArrayList<>());
        }

        public void addEdge(int src, int dest, int wght) {
            adjacencyList.get(src).add(new Edge(src, dest, wght));
        }

        public boolean detectCycle() {
            boolean[] visited = new boolean[vertices];
            boolean[] currentPath = new boolean[vertices];
            for (int i = 0; i < vertices; i++) {
                if (modifiedDFS(i, visited, currentPath)) {
                    return true;
                }
            }
            return false;
        }

        private boolean modifiedDFS(int vert, boolean[] visited, boolean[] currentPath) {
            visited[vert] = true;
            currentPath[vert] = true;
            for (Edge adjacentEdge : adjacencyList.get(vert)) {
                int adjacentVertex = adjacentEdge.getDest();
                if (!visited[adjacentVertex] && modifiedDFS(adjacentVertex, visited, currentPath)) {
                    return true;
                } else if (currentPath[adjacentVertex]) {
                    return true;
                }
            }
            currentPath[vert] = false;
            return false;
        }

    }

class Edge {
        private int src, dest, wght;

        Edge(int src, int dest, int wght) {
            this.src = src;
            this.dest = dest;
            this.wght = wght;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        public int getWght() {
            return wght;
        }
    }
    
    public boolean checkIfCycleExists(int vertices, int[][] edgeList) {
      // edge list contains edges with format [[dest, src]] .
        // create a directed graph and check if any cycle is there in the graph.
        if (numCourses == 0 || prerequisites.length == 0) {
            return true;
        }
        Graph graph = new Graph(numCourses);
        for (int[] edges : prerequisites) {
            graph.addEdge(edges[1], edges[0], 1);
        }
        return !graph.detectCycle();
    }
