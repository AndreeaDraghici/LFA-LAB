package com.Tema2.model;

import java.awt.Color;

public class Vertex {

    private String key;
    private String edgeKey;
    private String adjacentVertexKey;
    private Vertex adjacentVertex;
    private Vertex next;

    private final boolean dragging;
    protected int x;
    protected int y;
    protected int z;
    Color color;

    public Vertex(String key) {

        this.key = key;
        this.edgeKey = "";
        this.next = null;
        this.adjacentVertex = null;
        this.dragging = false;
        color = new Color(255, 153, 0);
    }

    public String getKey() {
        return key;
    }

    public Vertex getNext() {
        return this.next;
    }

    public Vertex getAdjacentVertex() {
        return this.adjacentVertex;
    }

    public String getEdgeKey() {
        return edgeKey;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Color getColor() {
        return color;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public void setAdjacentVertex(Vertex vertex) {
        this.adjacentVertex = vertex;
    }

    public void setEdgeKey(String edgeKey) {
        this.edgeKey = edgeKey;
    }

}
