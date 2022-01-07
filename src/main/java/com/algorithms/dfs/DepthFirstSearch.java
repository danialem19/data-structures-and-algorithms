package com.algorithms.dfs;

import com.data.structure.graph.impl1.Graph;
import com.data.structure.graph.impl1.Vertex;
import com.data.structure.graph.impl1.VertexDistancePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearch {
    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are traversing
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }

        Set<Vertex<T>> visitedSet = new java.util.HashSet<>();
        visitedSet.add(start);
        List<Vertex<T>> result = new java.util.ArrayList<>();
        result.add(start);
        Map<Vertex<T>, List<VertexDistancePair<T>>>
                adjacencyList = graph.getAdjacencyList();
        depthFirstSearchHelper(visitedSet, adjacencyList, start,  result);
        return result;
    }

    /**
     * A private recursive helper method that helps depthFirstSearch method
     * @param adjacencyList the Adjacency List of the Vertex currently pointed to
     * @param visitedSet a set that keeps track of visited vertices
     * @param curVertex the current vertex working wth
     * @param <T> the data type of the vertices
     * @param result list of vertices in the order they are visited
     */
    private static <T> void depthFirstSearchHelper(
            Set<Vertex<T>> visitedSet, Map<Vertex<T>,
            List<VertexDistancePair<T>>> adjacencyList, Vertex<T> curVertex,
            List<Vertex<T>> result) {
        for (VertexDistancePair<T> vertex : adjacencyList.get(curVertex)) {
            Vertex<T> temp = vertex.getVertex();
            if (!(visitedSet.contains(temp))) {
                result.add(temp);
                visitedSet.add(temp);
                depthFirstSearchHelper(visitedSet, adjacencyList, temp, result);
            }
        }
    }
}
