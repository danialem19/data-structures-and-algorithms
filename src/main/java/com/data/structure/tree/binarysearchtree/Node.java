package com.data.structure.tree.binarysearchtree;

public class Node <T extends Comparable<? super T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;

    /**
     * Create a BST node with the given data.
     *
     * @param data the data to be stored in this node.
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Get the data in this node.
     *
     * @return data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data in this node.
     *
     * @param data data to be placed into the node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get the node to the left of this node.
     *
     * @return node to the left.
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Set the node to the left of this node.
     *
     * @param left new node to the left.
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Get the node to the right of this node.
     *
     * @return node to the right.
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Set the node to the right of this node.
     *
     * @param right new node to the right.
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }
}
