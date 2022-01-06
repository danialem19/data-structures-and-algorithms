package com.data.structure.arraylist;

public class ArrayListImplement<E> {
    private E[] buffer; 
    private int currentSize;

    //Constructor
    public ArrayListImplement() {
        final int INITIAL_SIZE = 10; 
        buffer = (E[]) new Object[INITIAL_SIZE]; 
        currentSize = 0;
    }

    //Overwrites the element at the specified position, with provided element
    public void set(int pos, E element) {
        checkBounds(pos-1);
        buffer[pos-1] = element; 
    }

    //Return element from the requsted position in the ArrayList
    public E get(int pos) {
        checkBounds(pos-1);
        return buffer[pos-1]; 
    }

    //Add an element at the specified postion and returns the value removed
    public E addAtPos(int pos, E element) {
        checkBounds(pos-1);
        growBufferIfNecessary(); 
        E added = buffer[pos-1];
        for (int i = currentSize; i >= pos; i--) {
           buffer[i] = buffer[i - 1];
        }
        set(pos, element);
        currentSize++;
        return added;
    }

    //Removes an element from the specified postion and returns the value removed
    public E remove(int pos) {
        checkBounds(pos-1);
        E removed = buffer[pos-1];
        for (int i = pos; i < currentSize; i++) {
            buffer[i - 1] = buffer[i];
        }
        currentSize--;
        return removed;
    }

    //Adds element at the last location
    public boolean add(E newElement) {
        growBufferIfNecessary(); 
        currentSize++;
        buffer[currentSize - 1] = newElement;
        return true;
    }

    //Check if the request is not out of the size of the Array List
    private void checkBounds(int n) {
        if (n < 0 || n >= currentSize) {
            throw new IndexOutOfBoundsException(); 
        }
    }

    //Increases the size of the array is full
    private void growBufferIfNecessary() {
        if (currentSize == buffer.length) {
            E[] newBuffer = (E[]) new Object[2 * buffer.length];
            for (int i = 0; i < buffer.length; i++) {
                newBuffer[i] = buffer[i];
            }
            buffer = newBuffer;
        }
    }

    //Returns Size of the Array List
    public int size() { 
        return currentSize;
    } 

    public String toString() {
        String toString = "";
        if (currentSize >= 1) {
            toString += buffer[0];
        }
        for (int i = 1; i < currentSize; i++) {
            toString = toString + ", " + buffer[i];
        }
        return toString;
    }
}