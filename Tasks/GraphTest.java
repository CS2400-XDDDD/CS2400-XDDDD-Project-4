package Tasks;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {
    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>(5); // Create a graph with 5 vertices
        graph.setLabel(0, "A");
        graph.setLabel(1, "B");
        graph.setLabel(2, "C");
        graph.setLabel(3, "D");
        graph.setLabel(4, "E");
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(0, 1);
        assertTrue("Edge from A to B should exist", graph.isEdge(0, 1));
    }

    @Test
    public void testRemoveEdge() {
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        assertFalse("Edge from A to B should not exist", graph.isEdge(0, 1));
    }

    @Test
    public void testIsEdge() {
        graph.addEdge(0, 2);
        assertTrue("Edge from A to C should exist", graph.isEdge(0, 2));
        assertFalse("Edge from A to E should not exist", graph.isEdge(0, 4));
    }

    @Test
    public void testSetLabel() {
        graph.setLabel(3, "New D");
        assertEquals("Label should be 'New D'", "New D", graph.getLabel(3));
    }

    @Test
    public void testGetLabel() {
        assertEquals("Label should be 'B'", "B", graph.getLabel(1));
    }

    @Test
    public void testNeighbors() {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        int[] neighbors = graph.neighbors(0);
        int[] expectedNeighbors = {1, 2, 4};
        assertArrayEquals("Neighbors of A should be B, C, E", expectedNeighbors, neighbors);
    }

    @Test
    public void testSize() {
        assertEquals("Graph should have 5 vertices", 5, graph.size());
    }
}
