package com.mstvisualizer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create the graph
        Graph graph = new Graph(6); // Example graph with 6 vertices
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 10);

        // Step 2: Run Kruskal's MST algorithm
        MinimumSpanningTree mstAlgorithm = new MinimumSpanningTree();
        List<Graph.Edge> mst = mstAlgorithm.kruskalMST(graph);

        // Step 3: Print the MST
        System.out.println("Minimum Spanning Tree (MST) edges:");
        for (Graph.Edge edge : mst) {
            System.out.println("Edge from " + edge.source + " to " + edge.destination + " with weight " + edge.weight);
        }
    }
}
