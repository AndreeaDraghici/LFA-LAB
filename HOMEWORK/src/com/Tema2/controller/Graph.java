package com.Tema2.controller;

import com.Tema2.model.Vertex;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Graph {

    private Vertex head;
    private final Map<String, Vertex> vertexMap;
    private final boolean ordered;

    private final int SIZE = 30;

    public Graph() {

        this.vertexMap = new HashMap<>();
        this.ordered = true;
    }

    public Vertex getHead() {
        return head;
    }

    public Map<String, Vertex> getVertexMap() {
        return vertexMap;
    }

    public int getSIZE() {
        return SIZE;
    }

    public boolean addVertex(String key, int X, int Y) {

        Vertex newVertex = new Vertex(key);

        if (this.head == null) {
            newVertex.setX(X - SIZE / 2);
            newVertex.setY(Y - SIZE / 2);
            newVertex.setZ(SIZE);
        } else {
            if (this.searchVertex(key) == null) {
                newVertex.setX(X - SIZE / 2);
                newVertex.setY(Y - SIZE / 2);
                newVertex.setZ(SIZE);
                newVertex.setNextVertex(this.head);
            } else {
                return true;
            }

        }
        this.head = newVertex;
        this.vertexMap.put(newVertex.getKey(), newVertex);
        return false;
    }

    public void addEdge(String firstVertexKey, String secondVertexKey, String edgeKey, int x, int y) {

        Vertex vertex;
        Vertex secondVertex;
        Vertex newVertex;

        if (firstVertexKey.equals(secondVertexKey)) {
            JOptionPane.showMessageDialog(null, "Can't select the same vertex!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (this.searchVertex(secondVertexKey) == null) {

            return;
        }
        vertex = this.searchVertex(firstVertexKey);
        secondVertex = this.searchVertex(secondVertexKey);
        Vertex adjacentIterator = secondVertex.getAdjacentVertex();
        while (adjacentIterator != null) {
            if (adjacentIterator.getKey() == firstVertexKey) {
                JOptionPane.showMessageDialog(null, " There already exists an edge between " + secondVertexKey + " and "
                        + firstVertexKey + "!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            adjacentIterator = adjacentIterator.getNextVertex();
        }

        if (vertex != null) {
            newVertex = new Vertex(secondVertexKey);
            newVertex.setEdgeKey(edgeKey);
            if (vertex.getAdjacentVertex() == null) {
                newVertex.setX(x);
                newVertex.setY(y);
                newVertex.setZ(SIZE);
                vertex.setAdjacentVertex(newVertex);
            } else {
                if (vertex.getAdjacentVertex().getKey() != secondVertexKey) {
                    adjacentIterator = vertex.getAdjacentVertex();
                    while (adjacentIterator != null) {
                        if (adjacentIterator.getKey() == secondVertexKey) {
                            JOptionPane.showMessageDialog(null, " There already exists an edge between " + firstVertexKey + " and "
                                    + secondVertexKey + "!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        adjacentIterator = adjacentIterator.getNextVertex();
                    }
                    newVertex.setX(x);
                    newVertex.setY(y);
                    newVertex.setZ(SIZE);

                    Vertex adjacentVertex = vertex.getAdjacentVertex();
                    while (adjacentVertex.getNextVertex() != null) {
                        adjacentVertex = adjacentVertex.getNextVertex();
                    }
                    adjacentVertex.setNextVertex(newVertex);
                } else {
                    JOptionPane.showMessageDialog(null, " There already exists an edge between " + firstVertexKey + " and "
                            + secondVertexKey + "!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }

    private Vertex searchVertex(String key) {
        return this.vertexMap.get(key);
    }

    public void deleteVertex(String deletedVertexKey) {
        if (this.head.getKey() == deletedVertexKey) {
            this.head.setAdjacentVertex(null);
            this.head = this.head.getNextVertex();
        } else {
            Vertex deletedVertex = this.searchVertex(deletedVertexKey);
            deletedVertex.setAdjacentVertex(null);
            Vertex temp = this.head;
            Vertex prev = null;
            while (temp != null && temp.getKey() != deletedVertexKey) {
                prev = temp;
                temp = temp.getNextVertex();
            }
            prev.setNextVertex(temp.getNextVertex());
            System.gc();
        }
        this.vertexMap.remove(deletedVertexKey);
    }
}
