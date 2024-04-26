package ADTPackage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;  

public class LinkedQueueTest {
    private LinkedQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new LinkedQueue<>();
    }

    @Test
    public void testEnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals("Dequeue should return the first element enqueued", Integer.valueOf(1), queue.dequeue());
        assertEquals("Dequeue should return the next element", Integer.valueOf(2), queue.dequeue());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        queue.dequeue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue("Queue should be empty", queue.isEmpty());
        queue.enqueue(1);
        assertFalse("Queue should not be empty after enqueue", queue.isEmpty());
    }

    @Test
    public void testClear() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.clear();
        assertTrue("Queue should be empty after clear", queue.isEmpty());
    }
}
