package com.mstvisualizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumSpanningTree {
    private int[] parent;

    // Initialize parent array for union-find
    private void initializeParent(int vertices) {
        parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    // Find set of an element i (uses path compression)
    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    // Union of two sets
    private void union(int i, int j) {
        int iRep = find(i);
        int jRep = find(j);
        parent[iRep] = jRep;
    }

    // Kruskal's algorithm to find MST
    public List<Graph.Edge> kruskalMST(Graph graph) {
        List<Graph.Edge> result = new ArrayList<>();

        // Step 1: Sort edges by weight
        List<Graph.Edge> edges = graph.getEdges();
        Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        initializeParent(graph.getVertices());

        int edgeCount = 0;
        for (Graph.Edge edge : edges) {
            int sourceSet = find(edge.source);
            int destinationSet = find(edge.destination);

            // If including this edge does not cause a cycle
            if (sourceSet != destinationSet) {
                result.add(edge);
                union(sourceSet, destinationSet);
                edgeCount++;

                // Stop if we've found V-1 edges
                if (edgeCount == graph.getVertices() - 1) {
                    break;
                }
            }
        }

        return result;
    }
}
