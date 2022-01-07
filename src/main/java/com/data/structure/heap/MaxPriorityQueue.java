package com.data.structure.heap;

/**
 * Your implementation of a max priority queue.
 * 
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {
        backingHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Null Data Value used");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (backingHeap.isEmpty()) {
            throw new java.util.NoSuchElementException("There are not items to"
                    + " remove");
        }
        return (backingHeap.remove());
    }

    @Override
    public boolean isEmpty() {
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap = new MaxHeap<>();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT CHANGE THIS METHOD!
        return backingHeap;
    }
}
