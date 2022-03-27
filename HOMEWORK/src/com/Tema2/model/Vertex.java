package com.Tema2.model;

import java.awt.Color;

public class Vertex {

    private String key;
    private String edgeKey;
    private String adjacentVertexKey;
    private Vertex adjacentVertex;
    private Vertex nextVertex;
    protected int xCoord;
    protected int yCoord;
    protected int zCoord;
    Color color;

    public Vertex(String key) {

        this.key = key;
        this.edgeKey = "";
        this.nextVertex = null;
        this.adjacentVertex = null;
        color = new Color(255, 153, 0);
    }

    public String getKey() {
        return key;
    }

    public Vertex getNextVertex() {
        return this.nextVertex;
    }

    public Vertex getAdjacentVertex() {
        return this.adjacentVertex;
    }

    public String getEdgeKey() {
        return edgeKey;
    }


    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getzCoord() {
        return zCoord;
    }

    public Color getColor() {
        return color;
    }


    public void setX(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setY(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setZ(int zCoord) {
        this.zCoord = zCoord;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNextVertex(Vertex nextVertex) {
        this.nextVertex = nextVertex;
    }

    public void setAdjacentVertex(Vertex vertex) {
        this.adjacentVertex = vertex;
    }

    public void setEdgeKey(String edgeKey) {
        this.edgeKey = edgeKey;
    }

}
