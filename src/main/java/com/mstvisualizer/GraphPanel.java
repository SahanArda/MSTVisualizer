package com.mstvisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {
    private final Graph graph;
    private final List<Graph.Edge> edges; // Will either be original graph edges or MST edges
    private final boolean isMST; // Flag to identify if the panel is for the MST

    public GraphPanel(Graph graph, List<Graph.Edge> edges, boolean isMST) {
        this.graph = graph;
        this.edges = edges;
        this.isMST = isMST;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Positions for the vertices
        int[][] positions = {
                {100, 100}, {250, 50}, {400, 100}, {250, 200}, {100, 300}, {400, 300}
        };

        // The colour for the edges to black (for both original graph and MST)
        g.setColor(Color.BLACK);

        // Draw edges and their weights
        for (Graph.Edge edge : edges) {
            int x1 = positions[edge.source][0];
            int y1 = positions[edge.source][1];
            int x2 = positions[edge.destination][0];
            int y2 = positions[edge.destination][1];

            // Draw the edge (line)
            g.drawLine(x1, y1, x2, y2);

            // Draw the weight of the edge
            int weightX = (x1 + x2) / 2;
            int weightY = (y1 + y2) / 2;
            g.setColor(Color.RED); // Red for edge weights
            g.drawString(String.valueOf(edge.weight), weightX, weightY);
            g.setColor(Color.BLACK); // Reset to black for next edge
        }

        // Draw vertices as blue circles and label them with numbers
        g.setColor(Color.BLUE);
        for (int i = 0; i < graph.getVertices(); i++) {
            int x = positions[i][0];
            int y = positions[i][1];

            // Draw the circle representing the vertex
            g.fillOval(x - 10, y - 10, 20, 20); // Circle with radius 10

            // Draw the vertex number next to the circle
            g.setColor(Color.BLACK); // Use black for vertex numbers
            g.drawString(String.valueOf(i), x - 15, y - 15); // Slightly offset the number
            g.setColor(Color.BLUE); // Reset to blue for the next vertex
        }

        // Add a label on top to indicate if it's the MST or Original Graph
        g.setColor(Color.BLACK);
        g.drawString(isMST ? "Minimum Spanning Tree (MST)" : "Original Graph", 10, 20);
    }
}
