public class App {
    static class Graph {
        private int[][] adjMatrix;
        private String[] vertexData;
        private int size;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new String[size];
        }

        public void addEdge(int u, int v, int weight) {
            if (u >= 0 && u < size && v >= 0 && v < size) {
                adjMatrix[u][v] = weight;
                adjMatrix[v][u] = weight;  // For undirected graph
            }
        }

        public void addVertexData(int vertex, String data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public int[] dijkstra(String startVertexData) {
            int startVertex = findIndex(startVertexData);
            int[] distances = new int[size];
            boolean[] visited = new boolean[size];

            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
            }
            distances[startVertex] = 0;

            for (int i = 0; i < size; i++) {
                int u = minDistance(distances, visited);
                if (u == -1) break;

                visited[u] = true;
                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE) {
                        int newDist = distances[u] + adjMatrix[u][v];
                        if (newDist < distances[v]) {
                            distances[v] = newDist;
                        }
                    }
                }
            }
            return distances;
        }

        private int findIndex(String data) {
            for (int i = 0; i < size; i++) {
                if (vertexData[i].equals(data)) {
                    return i;
                }
            }
            return -1;
        }

        private int minDistance(int[] distances, boolean[] visited) {
            int min = Integer.MAX_VALUE, minIndex = -1;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && distances[v] <= min) {
                    min = distances[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        char vertexname;
        int vertexid;
        for (int i = 0; i < 7; i++) {
            vertexname = (char) (65 + i);
            vertexid = i;
            g.addVertexData(vertexid, Character.toString(vertexname));
        }
        int weight;
        int edgecount=10;
        for(int i=0;i<edgecount;i++){
            System.out.println("Enter the edge weight between two vertices");
            System.out.println("Enter the vertex 1");
            int vertex1 = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the vertex 2");
            int vertex2 = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the weight");
            weight = Integer.parseInt(System.console().readLine());
            g.addEdge(vertex1, vertex2, weight);
        }
        // Dijkstra's algorithm from D to all vertices
        System.out.println("Dijkstra's Algorithm starting from vertex D:\n");
        int[] distances = g.dijkstra("D");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Shortest distance from D to " + g.vertexData[i] + ": " + distances[i]);
        }
    }
}