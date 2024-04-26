package Tasks;

import ADTPackage.*;
import java.util.*;

public class DirectedGraph<T> implements GraphInterface<T> {
    private Map<T, Map<T, Double>> adjList;

    public DirectedGraph() {
        adjList = new HashMap<>();
    }

    @Override
    public boolean addVertex(T vertexLabel) {
        if (!adjList.containsKey(vertexLabel)) {
            adjList.put(vertexLabel, new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 1.0); // Default weight
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        if (!adjList.containsKey(begin)) {
            adjList.put(begin, new HashMap<>());
        }
        Map<T, Double> edges = adjList.get(begin);
        if (!edges.containsKey(end)) { // Check if the edge already exists
            edges.put(end, edgeWeight);
            return true;
        }
        return false; // Return false if edge already exists
    }



    @Override
    public boolean hasEdge(T begin, T end) {
        return adjList.containsKey(begin) && adjList.get(begin).containsKey(end);
    }

    @Override
    public boolean isEmpty() {
        return adjList.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        return adjList.size();
    }

    @Override
    public int getNumberOfEdges() {
        return adjList.values().stream().mapToInt(Map::size).sum();
    }

    @Override
    public void clear() {
        adjList.clear();
    }

    // Example use of entrySet() with the correct data structure
    public void printGraph() {
        for (Map.Entry<T, Map<T, Double>> vertexEntry : adjList.entrySet()) {
            T vertex = vertexEntry.getKey();
            System.out.println("Vertex " + vertex + " connects to:");
            for (Map.Entry<T, Double> edge : vertexEntry.getValue().entrySet()) {
                System.out.println("  - " + edge.getKey() + " with weight " + edge.getValue());
            }
        }
    }

    // Placeholder methods for shortest and least-cost path algorithms
    @Override
    public int getShortestPath(T start, T end, StackInterface<T> path) {
        Map<T, Double> distances = new HashMap<>();
        Map<T, T> predecessors = new HashMap<>();
        PriorityQueue<T> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<T> visited = new HashSet<>();

        for (T vertex : adjList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
            priorityQueue.add(vertex);
        }
        distances.put(start, 0.0);

        while (!priorityQueue.isEmpty()) {
            T current = priorityQueue.poll();
            if (current.equals(end)) {
                break;
            }

            for (Map.Entry<T, Double> neighborEntry : adjList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                if (visited.contains(neighbor)) continue;

                double weight = neighborEntry.getValue();
                double distanceThroughCurrent = distances.get(current) + weight;

                if (distanceThroughCurrent < distances.get(neighbor)) {
                    distances.put(neighbor, distanceThroughCurrent);
                    predecessors.put(neighbor, current);
                    priorityQueue.add(neighbor); // Update the priority queue
                }
            }
            visited.add(current);
        }

        // Build the path using the predecessors map
        if (distances.get(end) == Double.MAX_VALUE) {
            return -1; // No path
        }

        for (T step = end; step != null; step = predecessors.get(step)) {
            path.push(step);
        }

        return distances.get(end).intValue();
    }

    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        Map<T, Boolean> visited = new HashMap<>();
        QueueInterface<T> traversalOrder = new LinkedQueue<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(origin);
        visited.put(origin, true);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            traversalOrder.enqueue(current);

            Map<T, Double> neighbors = adjList.get(current);
            if (neighbors != null) {
                for (T neighbor : neighbors.keySet()) {
                    if (!visited.getOrDefault(neighbor, false)) {
                        visited.put(neighbor, true);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return traversalOrder;
    }

    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        Map<T, Boolean> visited = new HashMap<>();
        Stack<T> stack = new Stack<>();
        QueueInterface<T> traversalOrder = new LinkedQueue<>();

        stack.push(origin);

        while (!stack.isEmpty()) {
            T current = stack.pop();

            if (!visited.getOrDefault(current, false)) {
                visited.put(current, true);
                traversalOrder.enqueue(current);

                Map<T, Double> neighbors = adjList.get(current);
                if (neighbors != null) {
                    for (T neighbor : neighbors.keySet()) {
                        if (!visited.getOrDefault(neighbor, false)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
        return traversalOrder;
    }

    @Override
    public StackInterface<T> getTopologicalOrder() {
        StackInterface<T> stack = new LinkedStack<>();
        Map<T, Boolean> visited = new HashMap<>();

        for (T vertex : adjList.keySet()) {
            if (!visited.getOrDefault(vertex, false)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }
        return stack;
    }

    private void topologicalSortUtil(T vertex, Map<T, Boolean> visited, StackInterface<T> stack) {
        visited.put(vertex, true);
        Map<T, Double> neighbors = adjList.get(vertex);
        if (neighbors != null) {
            for (T neighbor : neighbors.keySet()) {
                if (!visited.getOrDefault(neighbor, false)) {
                    topologicalSortUtil(neighbor, visited, stack);
                }
            }
        }
        stack.push(vertex);
    }



    @Override
    public double getCheapestPath(T start, T end, StackInterface<T> path) {
        Map<T, Double> cost = new HashMap<>();
        Map<T, T> predecessors = new HashMap<>();

        for (T vertex : adjList.keySet()) {
            cost.put(vertex, Double.MAX_VALUE);
        }
        cost.put(start, 0.0);

        for (int i = 0; i < adjList.size() - 1; i++) {
            for (T u : adjList.keySet()) {
                for (Map.Entry<T, Double> entry : adjList.get(u).entrySet()) {
                    T v = entry.getKey();
                    double weight = entry.getValue();
                    if (cost.get(u) + weight < cost.get(v)) {
                        cost.put(v, cost.get(u) + weight);
                        predecessors.put(v, u);
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (T u : adjList.keySet()) {
            for (Map.Entry<T, Double> entry : adjList.get(u).entrySet()) {
                T v = entry.getKey();
                double weight = entry.getValue();
                if (cost.get(u) + weight < cost.get(v)) {
                    System.out.println("Graph contains a negative weight cycle");
                    return Double.NEGATIVE_INFINITY;
                }
            }
        }

        // Build the path using the predecessors map
        if (cost.get(end) == Double.MAX_VALUE) {
            return Double.POSITIVE_INFINITY; // No path found
        }

        LinkedList<T> pathList = new LinkedList<>();
        for (T step = end; step != null; step = predecessors.get(step)) {
            pathList.addFirst(step);
        }
        pathList.forEach(path::push);

        return cost.get(end);
    }
}
