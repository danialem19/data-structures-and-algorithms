package com.data.structure.tree.binarysearchtree;

import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
        size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Empty Collection used or null");
        }
        for (T i : data) {
            if (data == null) {
                throw new IllegalArgumentException("Data value in your collection");
            }
            add(i);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value");
        }
        BSTNode<T> newNode = new BSTNode<>(data);
        if (size == 0) {
            root = newNode;
            size++;
        } else {
            addNewNode(newNode, root);
        }
    }

    /**
     * Compares the data value in the new node with an existing node in the tree
     * . Performs recursive comparison the value of the new node with the
     * existing nodes in the tree. If the value of the new node is less, then
     * the comparison goes to the left child. if the value of the new node is
     * more, then the comparison goes to the right child. It is added when null
     * leaf node is reached.
     * @param newNode the new node to be added
     * @param existingNode the existing node in the tree
     */
    private void addNewNode(BSTNode<T> newNode, BSTNode<T> existingNode) {
        int comp = newNode.getData().compareTo(existingNode.getData());
        if (comp < 0) {
            if (existingNode.getLeft() == null) {
                existingNode.setLeft(newNode);
                size++;
            } else  {
                addNewNode(newNode, existingNode.getLeft());
            }
        } else if (comp > 0) {
            if (existingNode.getRight() == null) {
                existingNode.setRight(newNode);
                size++;
            } else  {
                addNewNode(newNode, existingNode.getRight());
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value is used");
        }
        BSTNode<T> removedNode = getRemovedNode(data, root, root);
        if (removedNode == null) {
            throw new java.util.NoSuchElementException("Data value not found");
        }
        return removedNode.getData();
    }

    /**
     *
     * @param data the data value to be removed
     * @param removedNodeParent the parent node of the node to be removed
     * @param removedNode the node value to be removed
     * @return the parent node of the node to be removed.
     */
    private BSTNode<T> getRemovedNode(T data, BSTNode<T> removedNodeParent,
                                      BSTNode<T> removedNode) {
        if (removedNode != null) {
            int comp = data.compareTo(removedNode.getData());
            if (comp == 0) {
                BSTNode<T> retun = new BSTNode<>(removedNode.getData());
                int comp2 = removedNodeParent.getData().compareTo(data);
                if (removedNode.getRight() == null && removedNode.getLeft()
                        == null) {
                    if (comp2 > 0) {
                        removedNodeParent.setLeft(null);
                    } else if (comp2 < 0) {
                        removedNodeParent.setRight(null);
                    } else {
                        root = null;
                    }
                } else if (removedNode.getLeft() == null) {
                    if (comp2 > 0) {
                        removedNodeParent.setLeft(removedNode.getRight());
                    } else if (comp2 < 0) {
                        removedNodeParent.setRight(removedNode.getRight());
                    } else {
                        root = root.getRight();
                    }
                } else if (removedNode.getRight() == null) {
                    if (comp2 > 0) {
                        removedNodeParent.setLeft(removedNode.getLeft());
                    } else if (comp2 < 0) {
                        removedNodeParent.setRight(removedNode.getLeft());
                    } else {
                        root = root.getLeft();
                    }
                } else {
                    BSTNode<T> successorParent = removedNode;
                    BSTNode<T> successor = removedNode.getRight();
                    while (successor.getLeft() != null) {
                        successorParent = successor;
                        successor = successor.getLeft();
                    }
                    removedNode.setData(successor.getData());
                    if (successorParent.equals(removedNode)) {
                        successorParent.setRight(successor.getRight());
                    } else {
                        successorParent.setLeft(successor.getRight());
                    }
                }
                size--;
                return retun;
            } else if (comp > 0) {
                return getRemovedNode(data, removedNode, removedNode.
                        getRight());
            } else {
                return getRemovedNode(data, removedNode, removedNode.getLeft());
            }
        }
        return null;
    }

    /**
     *
     * @param data The data value to be checked for existence in the tree.
     * @param nodeLocator The node location where the value is located
     * @return The location of the node that is found or null if not found
     */
    private BSTNode<T> findData(T data, BSTNode<T> nodeLocator) {
        if (nodeLocator != null) {
            int comp = data.compareTo(nodeLocator.getData());
            if (comp == 0) {
                return nodeLocator;
            } else if (comp > 0) {
                return findData(data, nodeLocator.getRight());
            } else {
                return findData(data, nodeLocator.getLeft());
            }
        }
        return nodeLocator;

    }
    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value");
        }
        BSTNode<T> nodeLocator = findData(data, root);
        if (nodeLocator == null) {
            throw new java.util.NoSuchElementException("Data is not found");
        }
        return nodeLocator.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value");
        }
        BSTNode<T> startAt = root;
        while (startAt != null) {
            int comp = startAt.getData().compareTo(data);
            if (comp == 0) {
                return true;
            } else if (comp > 0) {
                startAt = startAt.getLeft();
            } else {
                startAt = startAt.getRight();
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = preOrderHelper(root, lst);
        return lst;
    }

    /**
     *
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The Pre-order in list data structure
     */
    private List<T> preOrderHelper(BSTNode<T> node, List<T> lst) {
        if (node != null) {
            lst.add(node.getData());
            preOrderHelper(node.getLeft(), lst);
            preOrderHelper(node.getRight(), lst);
        }
        return lst;
    }

    @Override
    public List<T> postorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = postOrderHelper(root, lst);
        return lst;
    }

    /**
     *
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The Post-order in List Data Sturacture
     */
    private List<T> postOrderHelper(BSTNode<T> node, List<T> lst) {
        if (node != null) {
            postOrderHelper(node.getLeft(), lst);
            postOrderHelper(node.getRight(), lst);
            lst.add(node.getData());
        }
        return lst;
    }

    @Override
    public List<T> inorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = inorderHelper(root, lst);
        return lst;
    }

    /**
     *
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return the inorder traversal inside List data structure
     */
    private List<T> inorderHelper(BSTNode<T> node, List<T> lst) {
        if (node != null) {
            inorderHelper(node.getLeft(), lst);
            lst.add(node.getData());
            inorderHelper(node.getRight(), lst);
        }
        return lst;
    }

    @Override
    public List<T> levelorder() {
        List<T> lst = new java.util.ArrayList<>();
        if (size == 0) {
            return lst;
        }
        java.util.Queue<BSTNode<T>> qq = new java.util.LinkedList<>();
        qq.add(root);
        BSTNode<T> level;
        while (!qq.isEmpty()) {
            level = qq.poll();
            lst.add(level.getData());
            if (level.getLeft() != null) {
                qq.add(level.getLeft());
            }
            if (level.getRight() != null) {
                qq.add(level.getRight());
            }
        }
        return lst;
    }

    /**
     *
     * @param qq The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The level order traversal inside List data structure
     */
    private List<T> levelOrderHelper(java.util.Queue<BSTNode<T>> qq,
                                     List<T> lst) {
        BSTNode<T> level;
        while (!qq.isEmpty()) {
            level = qq.poll();
            lst.add(level.getData());
            if (level.getLeft() != null) {
                qq.add(level.getLeft());
            }
            if (level.getRight() != null) {
                qq.add(level.getRight());
            }
        }
        return lst;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (size == 0) {
            return -1;
        }
        return heightHelper(root);
    }

    /**
     *
     * @param start the starting node which is the root
     * @return The height of the root node
     */
    private int heightHelper(BSTNode<T> start) {
        int right;
        int left;
        if (start == null) {
            return -1;
        } else {
            right = heightHelper(start.getRight());
            left = heightHelper(start.getLeft());
        }
        return Math.max(right + 1, left + 1);
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
