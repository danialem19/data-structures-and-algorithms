package com.algorithms.dijkstras;

import com.data.structure.graph.impl1.Graph;
import com.data.structure.graph.impl1.Vertex;
import com.data.structure.graph.impl1.VertexDistancePair;

import java.util.List;
import java.util.Map;

public class Dijkstras {
    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }
        java.util.PriorityQueue<VertexDistancePair<T>> next = new
                java.util.PriorityQueue<>();
        next.add(new VertexDistancePair<>(start, 0));

        Map<Vertex<T>, Integer> result = new java.util.HashMap<>();
        result.put(start, 0);
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyMap
                = graph.getAdjacencyList();
        while (!(next.isEmpty())) {
            VertexDistancePair<T> curVertex = next.remove();
            List<VertexDistancePair<T>> neighbor
                    = adjacencyMap.get(curVertex.getVertex());
            for (VertexDistancePair<T> x : neighbor) {
                Vertex<T> tempV = x.getVertex();
                int tempD = curVertex.getDistance() + x.getDistance();
                if (result.containsKey(tempV)) {
                    if (result.get(tempV) > tempD) {
                        VertexDistancePair<T> add = new
                                VertexDistancePair<T>(tempV, tempD);
                        result.replace(tempV, tempD);
                        next.add(add);
                    }
                } else {
                    result.put(tempV, tempD);
                    next.add(new VertexDistancePair<T>(
                            tempV, tempD));
                }
            }
        }
        for (Vertex<T> element : adjacencyMap.keySet()) {
            if (!result.containsKey(element)) {
                result.put(element, Integer.MAX_VALUE);
            }
        }
        return result;
    }
}
