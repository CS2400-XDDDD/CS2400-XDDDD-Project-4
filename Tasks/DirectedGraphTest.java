package Tasks;

import ADTPackage.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectedGraphTest {
    private DirectedGraph<String> graph;

    @Before
    public void setUp() {
        graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "A", 3.0);
    }

    @Test
    public void testAddVertex() {
        assertTrue(graph.addVertex("D"));
        assertFalse(graph.addVertex("A")); // "A" is already added
    }

    @Test
    public void testAddEdge() {
        assertTrue(graph.addEdge("A", "C", 1.5));
        assertFalse(graph.addEdge("A", "B", 0.5)); // Edge A->B already exists, assumes you can't have parallel edges
    }

    @Test
    public void testHasEdge() {
        assertTrue(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A")); // No back edge
    }

    @Test
    public void testIsEmpty() {
        assertFalse(graph.isEmpty());
        graph.clear();
        assertTrue(graph.isEmpty());
    }

    @Test
    public void testGetNumberOfVertices() {
        assertEquals(3, graph.getNumberOfVertices());
    }

    @Test
    public void testGetNumberOfEdges() {
        assertEquals(3, graph.getNumberOfEdges());
    }

    @Test
    public void testGetBreadthFirstTraversal() {
        QueueInterface<String> bfs = graph.getBreadthFirstTraversal("A");
        assertEquals("A", bfs.dequeue());
        assertEquals("B", bfs.dequeue());
        assertEquals("C", bfs.dequeue());
    }

    @Test
    public void testGetDepthFirstTraversal() {
        QueueInterface<String> dfs = graph.getDepthFirstTraversal("A");
        assertEquals("A", dfs.dequeue());
        assertEquals("B", dfs.dequeue());
        assertEquals("C", dfs.dequeue());
    }

    @Test
    public void testGetTopologicalOrder() {
        DirectedGraph<String> dag = new DirectedGraph<>();
        dag.addVertex("X");
        dag.addVertex("Y");
        dag.addVertex("Z");
        dag.addEdge("X", "Y", 1.0);
        dag.addEdge("Y", "Z", 1.0);
        StackInterface<String> topologicalOrder = dag.getTopologicalOrder();

        StackInterface<String> reversedOrder = new LinkedStack<>();
        while (!topologicalOrder.isEmpty()) {
            reversedOrder.push(topologicalOrder.pop());
        }

        assertEquals("Z", reversedOrder.pop());
        assertEquals("Y", reversedOrder.pop());
        assertEquals("X", reversedOrder.pop());
}

    @Test
    
    public void testGetShortestPath() {
        DirectedGraph<String> graphSP = new DirectedGraph<>();
        graphSP.addVertex("A");
        graphSP.addVertex("B");
        graphSP.addVertex("C");
        graphSP.addEdge("A", "B", 1.0);
        graphSP.addEdge("B", "C", 1.0);
        graphSP.addEdge("A", "C", 10.0); // Direct but longer path
        StackInterface<String> path = new LinkedStack<>();
        int shortestPath = graphSP.getShortestPath("A", "C", path);

        StackInterface<String> correctOrderPath = new LinkedStack<>();
        while (!path.isEmpty()) {
            correctOrderPath.push(path.pop());
        }

        assertEquals(2, shortestPath);
        assertEquals("C", correctOrderPath.pop());
        assertEquals("B", correctOrderPath.pop());
        assertEquals("A", correctOrderPath.pop());
    }

    @Test
    public void testGetCheapestPath() {
        StackInterface<String> path = new LinkedStack<>();
        double cheapestPath = graph.getCheapestPath("A", "C", path);
        assertEquals(3.0, cheapestPath, 0.001);
        assertEquals("C", path.pop());
        assertEquals("B", path.pop());
        assertEquals("A", path.pop());
    }
}
