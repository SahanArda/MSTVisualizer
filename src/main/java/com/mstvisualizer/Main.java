package com.mstvisualizer;

import javax.swing.*;
import java.awt.*;
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

        // Step 4: Create the Swing frame to display both graphs
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400); // Make the window wider to fit both panels

        // Set layout manager for the frame to arrange panels horizontally
        frame.setLayout(new GridLayout(1, 2));

        // Add the GraphPanel for the original graph
        GraphPanel originalGraphPanel = new GraphPanel(graph, graph.getEdges(), false);
        frame.add(originalGraphPanel);

        // Add the GraphPanel for the MST
        GraphPanel mstGraphPanel = new GraphPanel(graph, mst, true);
        frame.add(mstGraphPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
