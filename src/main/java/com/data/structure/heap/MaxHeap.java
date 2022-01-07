package com.data.structure.heap;

/**
 * Your implementation of a max heap.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial length of {@code INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray[0] = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Null data value not allowed");
        }
        int nextIndex = size + 1;
        if (size == 0) {
            backingArray[nextIndex] = item;
            size++;
        } else  {
            if (nextIndex == backingArray.length) {
                T[] newArray = (T[]) new
                        Comparable[(2 * backingArray.length) + 1];
                for (int i = 0; i < backingArray.length; i++) {
                    newArray[i] = backingArray[i];
                }
                backingArray = newArray;
            }

            backingArray[nextIndex] = item;
            size++;
            int index = size;
            while (index > 1 && backingArray[index / 2]
                    .compareTo(backingArray[index]) < 0) {
                T child = backingArray[index];
                backingArray[index] = backingArray[index / 2];
                backingArray[index / 2] = child;
                index = index / 2;
            }
        }

    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap is empty");
        }

        T rootRemoved = backingArray[1];
        if (size > 1) {
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
        } else {
            backingArray[1] = null;
        }
        size--;
        T newRoot = backingArray[1];
        boolean next = false;
        int index = 1;
        while (!next && size >= 2) {
            int childIndex = index * 2;
            if (childIndex <= size) {
                T child = backingArray[childIndex];
                int rightChildIndex = (index * 2) + 1;
                if (rightChildIndex <= size) {
                    T rightChild = backingArray[rightChildIndex];
                    if (rightChild.compareTo(child) > 0) {
                        childIndex = rightChildIndex;
                        child = rightChild;
                    }
                }
                if (child.compareTo(newRoot) > 0) {
                    backingArray[index] = child;
                    index = childIndex;
                } else {
                    next = true;
                }
            } else {
                next = true;
            }
        }
        backingArray[index] = newRoot;
        return rootRemoved;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray[0] = null;
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
