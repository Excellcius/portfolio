import java.util.*;

public class GraphTraversal {

    public static void main(String[] args) {
        // Creating an undirected graph
        Graph undirectedGraph = new Graph();
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(2, 0);
        undirectedGraph.addEdge(2, 3);
        undirectedGraph.addEdge(3, 3);

        System.out.println("Undirected Graph:");
        undirectedGraph.printGraph();

        System.out.println("\nDFS Traversal:");
        undirectedGraph.dfsTraversal(0);

        System.out.println("\nBFS Traversal:");
        undirectedGraph.bfsTraversal(0);

        // Creating a directed graph
        Graph directedGraph = new Graph(true);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 0);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 3);

        System.out.println("\nDirected Graph:");
        directedGraph.printGraph();

        System.out.println("\nDFS Traversal:");
        directedGraph.dfsTraversal(0);

        System.out.println("\nBFS Traversal:");
        directedGraph.bfsTraversal(0);
    }
}

class Graph {
    private final Map<Integer, List<Integer>> adjacencyList;
    private final boolean isDirected;

    public Graph() {
        this(false);
    }

    public Graph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int source, int destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        if (!isDirected) {
            adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
        }
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }
    }

    public void dfsTraversal(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfs(startVertex, visited);
    }

    private void dfs(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : adjacencyList.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    public void bfsTraversal(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjacencyList.getOrDefault(currentVertex, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }
}
