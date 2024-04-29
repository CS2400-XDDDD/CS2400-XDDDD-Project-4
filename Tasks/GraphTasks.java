package Tasks;

import ADTPackage.*; // Assume appropriate types are in this package


public class GraphTasks {
    public static void main(String[] args) {
        // Instantiate the graph
        DirectedGraph<Character> graph = new DirectedGraph<>();

        // Add vertices
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        for (char vertex : vertices) {
            graph.addVertex(vertex);
        }

        // Add edges as specified
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'F');
        graph.addEdge('E', 'H');
        graph.addEdge('G', 'H');
        graph.addEdge('F', 'C');
        graph.addEdge('F', 'H');
        graph.addEdge('H', 'I');
        graph.addEdge('C', 'B');
        graph.addEdge('I', 'F');

        // Perform a breadth-first traversal and collect the order
        QueueInterface<Character> bfsOrder = graph.getBreadthFirstTraversal('A');

        // Format the output for BFS traversal
        StringBuilder traversalOutput = new StringBuilder("The graph: {V, E} where V={ ");
        for (char vertex : vertices) {
            traversalOutput.append(vertex).append(", ");
        }
        traversalOutput = new StringBuilder(traversalOutput.substring(0, traversalOutput.length() - 2)); // Remove trailing comma and space
        traversalOutput.append(" }, E = { ");

        // List edges
        traversalOutput.append("(A, B), (A, D), (A, E), (B, E), (D, G), (E, F), (E, H), (G, H), (F, C), (F, H), (H, I), (C, B), (I, F) }");

        // Print the graph structure
        System.out.println(traversalOutput);

        // Prepare and print BFS traversal order
        StringBuilder bfsOutput = new StringBuilder("BFS Traversal starting from node A: ");
        while (!bfsOrder.isEmpty()) {
            bfsOutput.append(bfsOrder.dequeue()).append(" ");
        }

        System.out.println(bfsOutput);


        QueueInterface<Character> dfsOrder = graph.getDepthFirstTraversal('A');

        StringBuilder traversalDOutput = new StringBuilder("The graph: {V, E} where V={ ");
        for (char vertex : vertices) {
            traversalDOutput.append(vertex).append(", ");
        }

        traversalDOutput = new StringBuilder(traversalDOutput.substring(0, traversalDOutput.length() - 2)); // Remove trailing comma and space
        traversalDOutput.append(" }, E = { ");

        // List edges
        traversalDOutput.append("(A, E), (E, H), (H, I), (E, F), (F, C), (C, B), (A, D), (D, G) }");

        // Print the graph structure
        System.out.println(traversalDOutput);

        // Prepare and print BFS traversal order
        StringBuilder dfsOutput = new StringBuilder("DFS Traversal starting from node A: ");
        while (!dfsOrder.isEmpty()) {
            dfsOutput.append(dfsOrder.dequeue()).append(" ");
        }

        System.out.println(dfsOutput);


        char[] bfsTree = {'A','B','D','E',' ','G',' ','F','H',' ',' ',' ',' ','C',' ', 'I'};
        

        char[] dfsTree = {'A','E','D','H','F','G',' ','I',' ','C',' ',' ',' ',' ',' ',' ',' ','B'};

        for(int x = 0; x < bfsTree.length; x++) {
            System.out.print(x + "." + bfsTree[x] + " ");
        }

        System.out.println("");

        for(int y = 0; y < dfsTree.length; y++) {
            System.out.print(y + "." + dfsTree[y] + " ");
        }

    }
}