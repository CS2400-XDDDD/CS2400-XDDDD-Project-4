package ADTPackage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;  

public class LinkedStackTest {
    private LinkedStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new LinkedStack<>();
    }

    @Test
    public void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        assertEquals("Pop should return the last element pushed", Integer.valueOf(2), stack.pop());
        assertEquals("Pop should return the next-to-last element", Integer.valueOf(1), stack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopEmpty() {
        stack.pop();
    }

    @Test
    public void testIsEmpty() {
        assertTrue("Stack should be empty", stack.isEmpty());
        stack.push(1);
        assertFalse("Stack should not be empty after push", stack.isEmpty());
    }

    @Test
    public void testClear() {
        stack.push(1);
        stack.push(2);
        stack.clear();
        assertTrue("Stack should be empty after clear", stack.isEmpty());
    }
}
