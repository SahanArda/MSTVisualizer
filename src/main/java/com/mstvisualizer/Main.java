package com.mstvisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertices = 0;
        int edges = 0;

        // Step 1: Get the number of vertices with input validation
        while (true) {
            try {
                System.out.print("Enter the number of vertices: ");
                vertices = scanner.nextInt();
                if (vertices <= 0) {
                    System.out.println("The number of vertices must be a positive integer. Please try again.");
                    continue;
                }
                break; // Exit the loop when a valid input is provided
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Create the graph with the specified number of vertices
        Graph graph = new Graph(vertices);

        // Step 2: Get the number of edges with input validation
        while (true) {
            try {
                System.out.print("Enter the number of edges: ");
                edges = scanner.nextInt();
                if (edges < 0) {
                    System.out.println("The number of edges cannot be negative. Please try again.");
                    continue;
                }
                break; // Exit the loop when a valid input is provided
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Step 3: Get the details for each edge with input validation
        for (int i = 0; i < edges; i++) {
            int source = -1;
            int destination = -1;
            int weight = -1;

            // Get the source vertex with input validation
            while (true) {
                try {
                    System.out.print("Enter source vertex for edge " + (i + 1) + ": ");
                    source = scanner.nextInt();
                    if (source < 0 || source >= vertices) {
                        System.out.println("Invalid source vertex. Please enter a value between 0 and " + (vertices - 1) + ".");
                        continue;
                    }
                    break; // Exit the loop when a valid input is provided
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Get the destination vertex with input validation
            while (true) {
                try {
                    System.out.print("Enter destination vertex for edge " + (i + 1) + ": ");
                    destination = scanner.nextInt();
                    if (destination < 0 || destination >= vertices) {
                        System.out.println("Invalid destination vertex. Please enter a value between 0 and " + (vertices - 1) + ".");
                        continue;
                    }
                    break; // Exit the loop when a valid input is provided
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Get the weight with input validation
            while (true) {
                try {
                    System.out.print("Enter weight for edge " + (i + 1) + ": ");
                    weight = scanner.nextInt();
                    if (weight < 0) {
                        System.out.println("Weight must be a positive integer. Please try again.");
                        continue;
                    }
                    break; // Exit the loop when a valid input is provided
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Add the edge to the graph
            graph.addEdge(source, destination, weight);
        }

        // Step 4: Run Kruskal's MST algorithm
        MinimumSpanningTree mstAlgorithm = new MinimumSpanningTree();
        List<Graph.Edge> mst = mstAlgorithm.kruskalMST(graph);

        // Step 5: Print the MST
        System.out.println("Minimum Spanning Tree (MST) edges:");
        for (Graph.Edge edge : mst) {
            System.out.println("Edge from " + edge.source + " to " + edge.destination + " with weight " + edge.weight);
        }

        // Step 6: Create the Swing frame to display both graphs
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
