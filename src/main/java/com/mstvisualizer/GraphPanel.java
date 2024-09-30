package com.mstvisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {
    private final Graph graph;
    private final List<Graph.Edge> edges;
    private final boolean isMST;  // Flag to differentiate between original graph and MST

    public GraphPanel(Graph graph, List<Graph.Edge> edges, boolean isMST) {
        this.graph = graph;
        this.edges = edges;
        this.isMST = isMST;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the size of the panel to dynamically calculate positions
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int radius = Math.min(panelWidth, panelHeight) / 3; // Radius for circular layout

        // Calculate positions of vertices in a circular layout
        int[][] positions = new int[graph.getVertices()][2];
        for (int i = 0; i < graph.getVertices(); i++) {
            double angle = 2 * Math.PI * i / graph.getVertices() - Math.PI / 2; // Start from the top
            int x = (int) (panelWidth / 2 + radius * Math.cos(angle)); // X-coordinate
            int y = (int) (panelHeight / 2 + radius * Math.sin(angle)); // Y-coordinate
            positions[i][0] = x;
            positions[i][1] = y;
        }

        // Draw edges (both for original graph and MST)
        g.setColor(isMST ? Color.GREEN : Color.BLACK); // Use green for MST, black for original graph
        for (Graph.Edge edge : edges) {
            int x1 = positions[edge.source][0];
            int y1 = positions[edge.source][1];
            int x2 = positions[edge.destination][0];
            int y2 = positions[edge.destination][1];
            g.drawLine(x1, y1, x2, y2); // Draw line for the edge

            // Draw the weight of the edge in the middle
            int weightX = (x1 + x2) / 2;
            int weightY = (y1 + y2) / 2;
            g.setColor(Color.RED); // Use red for weights
            g.drawString(String.valueOf(edge.weight), weightX, weightY); // Display the weight of the edge
            g.setColor(isMST ? Color.GREEN : Color.BLACK); // Reset to green/black for next edge
        }

        // Draw vertices as blue circles and label them with numbers
        g.setColor(Color.BLUE);
        for (int i = 0; i < graph.getVertices(); i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            g.fillOval(x - 10, y - 10, 20, 20); // Circle with radius 10
            g.setColor(Color.BLACK); // Use black for vertex numbers
            g.drawString(String.valueOf(i), x - 5, y - 15); // Draw vertex number
        }
    }
}
