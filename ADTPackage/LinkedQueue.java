package ADTPackage;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T> {
    private LinkedList<T> list;

    public LinkedQueue() {
        list = new LinkedList<T>();
    }

    @Override
    public void enqueue(T newEntry) {
        list.addLast(newEntry);
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Error: queue is empty");
        return list.removeFirst();
    }

    @Override
    public T getFront() {
        if (isEmpty())
            throw new NoSuchElementException("Error: queue is empty");
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }
}
