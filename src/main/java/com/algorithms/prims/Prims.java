package com.algorithms.prims;

import com.data.structure.graph.impl1.Edge;
import com.data.structure.graph.impl1.Graph;
import com.data.structure.graph.impl1.Vertex;
import com.data.structure.graph.impl1.VertexDistancePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prims {
    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return null.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may assume that for a given starting vertex, there will only be
     * one valid MST that can be formed. In addition, only an undirected graph
     * will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are creating the MST for
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }

        Set<Edge<T>> result = new java.util.HashSet<>();
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyMap = graph.getAdjacencyList();

        java.util.PriorityQueue<VertexDistancePair<T>> workOn = new java.util.PriorityQueue<>();
        workOn.addAll(adjacencyMap.get(start));

        java.util.PriorityQueue<VertexDistancePair<T>> next = new java.util.PriorityQueue<>();
        next.addAll(adjacencyMap.get(start));

        Set<Vertex<T>> visitedSet = new java.util.HashSet<>();

        while (!(next.isEmpty())) {
            VertexDistancePair<T> curVertex = next.peek();
            int minDistance = curVertex.getDistance();
            Vertex<T> minVertexTo = curVertex.getVertex();

            for (VertexDistancePair<T> x : next) {
                if(x.getDistance() < minDistance && !visitedSet.contains(x.getVertex())) {
                    curVertex = x;
                    minDistance = x.getDistance();
                    minVertexTo = x.getVertex();
                }
            }
            result.add(new Edge<>(start, minVertexTo, minDistance, graph.isDirected()));

            visitedSet.add(curVertex.getVertex());
            next.remove(curVertex);
        }
        return result;
    }
}
