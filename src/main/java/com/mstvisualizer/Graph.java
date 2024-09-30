package com.mstvisualizer;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static class Edge {
        public int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private final int vertices;
    private final List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getVertices() {
        return vertices;
    }
}
