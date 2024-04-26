package ADTPackage;

import java.util.LinkedList;
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private LinkedList<T> stack;

    public LinkedStack() {
        stack = new LinkedList<T>();
    }

    @Override
    public void push(T newEntry) {
        stack.addFirst(newEntry);
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void clear() {
        stack.clear();
    }
}
