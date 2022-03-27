package com.Tema2.controller;

import com.Tema2.model.Vertex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serial;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Draw extends JPanel implements MouseListener, MouseMotionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    private String action;
    private String information;
    private final Graph graph;
    private boolean selectedEdge;
    private String currentVertex;

    public Draw() {

        this.graph = new Graph();
        this.selectedEdge = false;
        this.currentVertex = "";

        this.setAction("Vertex");
        this.setDoubleBuffered(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void mouseClicked(MouseEvent e) {
        switch (action) {
            case "Vertex":
                String vertexKey;
                vertexKey = JOptionPane.showInputDialog("Add a new vertex");
                // vertices can't have a null key
                if (vertexKey != null && !vertexKey.equals("")) {
                    // vertex with the same key already exists
                    if (graph.addVertex(vertexKey, e.getX(), e.getY())) {
                        JOptionPane.showMessageDialog(null, "The vertex " + vertexKey + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Edge":
                String selectedVertexKey = this.searchByCoordinates(e.getX(), e.getY());
                if (selectedVertexKey != null) {
                    // select 2 vertices for an edge
                    if (!selectedEdge) {
                        this.currentVertex = selectedVertexKey;
                        this.selectedEdge = true;
                    } else {
                        this.repaint();
                        String edgeKey = JOptionPane.showInputDialog("Add a new edge");
                        if (edgeKey != null) {
                            this.graph.addEdge(this.currentVertex, selectedVertexKey, edgeKey, e.getX(), e.getY());
                        }
                        this.resetVerticesColors();
                        this.currentVertex = "";
                        this.selectedEdge = false;
                    }
                }
                break;
            default:
                if ("Drag".equals(action)) {
                } else {
                    if ("Delete".equals(action)) {
                        String deletedVertexKey = this.searchByCoordinates(e.getX(), e.getY());
                        if (deletedVertexKey != null) {
                            this.repaint();
                            int input = JOptionPane.showConfirmDialog(null, "Delete vertex " + deletedVertexKey + "?");
                            if (input == 0) {
                                this.graph.deleteVertex(deletedVertexKey);
                            } else {
                                this.resetVerticesColors();
                            }
                        }
                    }
                }
                break;
        }
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D dosD = (Graphics2D) g;

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        dosD.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.drawVertices(g);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(BLACK);
        g.drawString(this.action, 10, 25);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString(information, 10, 40);
    }

    //display the info depending on the action selected and update current action
    public void setAction(String action) {
        this.action = action;
        if ("Vertex".equals(action)) {
            this.resetVerticesColors();
            this.information = "Click on the screen to add vertices";
        } else if ("Edge".equals(action)) {
            this.resetVerticesColors();
            this.information = "Click on vertices to join them";
        } else if ("Drag".equals(action)) {
            this.information = "Click on a vertex and drag it across the screen";
        } else if ("Delete".equals(action)) {
            this.information = "Click on a vertex to remove it";
        }
        this.repaint();
    }

    public void mouseReleased(MouseEvent event) {
        if (this.action.equals("Drag")) {
            this.resetVerticesColors();
            this.currentVertex = "";
        }
    }

    // search for the vertex by coordinates and update them
    public void mouseDragged(MouseEvent event) {

        if (this.action.equals("Drag")) {
            if (this.currentVertex.equals("")) {
                String foundVertexKey = this.searchByCoordinates(event.getX(), event.getY());
                if (foundVertexKey == null) {
                    return;
                }
                this.currentVertex = foundVertexKey;
            }
            this.updateCoordinates(this.currentVertex, event.getX(), event.getY());
            this.repaint();
        }
    }

    public void drawVertices(Graphics graphics) {

        Vertex vertex = this.graph.getHead();
        if (vertex == null) {
            return;
        }
        this.drawEdges(graphics);
        while (vertex != null) {
            graphics.setColor(vertex.getColor());
            graphics.fillOval(vertex.getxCoord(), vertex.getyCoord(), vertex.getzCoord(), vertex.getzCoord());
            graphics.setColor(BLACK);
            graphics.drawString(vertex.getKey(), vertex.getxCoord(), vertex.getyCoord() - 10);
            vertex = vertex.getNextVertex();
        }
    }

    private void drawEdges(Graphics graphics) {

        Vertex head = this.graph.getHead();
        if (head == null) {
            return;
        }
        Vertex vertex;
        graphics.setColor(BLACK);
        while (head != null) {
            vertex = head.getAdjacentVertex();
            while (vertex != null && graph.getVertexMap().containsKey(vertex.getKey())) {
                graphics.drawString(vertex.getEdgeKey(), this.getAverageDistance(head.getxCoord(), vertex.getxCoord()), this.getAverageDistance(head.getyCoord(), vertex.getyCoord()));
                graphics.drawLine(vertex.getxCoord(), vertex.getyCoord(), head.getxCoord() + (this.graph.getSIZE() / 2), head.getyCoord() + (this.graph.getSIZE() / 2));
                vertex = vertex.getNextVertex();
            }
            head = head.getNextVertex();
        }
    }

    // searches a vertex by its coordinates and returns its key if found
    public String searchByCoordinates(int xCoordinate, int yCoordinate) {
        Vertex head = this.graph.getHead();
        if (head == null) {
            return null;
        }
        if (xCoordinate > head.getxCoord() && xCoordinate < head.getxCoord() + this.graph.getSIZE() && yCoordinate > head.getyCoord() && yCoordinate < head.getyCoord() + this.graph.getSIZE()) {
            head.setColor(Color.RED);
            return head.getKey();
        } else {
            head = head.getNextVertex();
        }
        while (head != null) {
            if (xCoordinate > head.getxCoord() && xCoordinate < head.getxCoord() + this.graph.getSIZE() && yCoordinate > head.getyCoord() && yCoordinate < head.getyCoord() + this.graph.getSIZE()) {
                head.setColor(Color.RED);
                return head.getKey();
            } else {
                head = head.getNextVertex();
            }
        }
        return null;
    }

    public void resetVerticesColors() {

        Vertex head = this.graph.getHead();
        if (head == null) {
            return;
        }
        while (head != null) {
            head.setColor(new Color(0xd9820f));
            head = head.getNextVertex();
        }
    }

    public void resetColor(String key) {
        if (this.graph.getVertexMap().containsKey(key)) {
            Vertex node = this.graph.getVertexMap().get(key);
            node.setColor(new Color(0xd9820f));
            this.repaint();
        }
    }

    public void updateCoordinates(String key, int xCoordinate, int yCoordinate) {
        Vertex head = this.graph.getHead();
        while (head != null) {
            if (head.getKey().equals(key)) {
                head.setX(xCoordinate - this.graph.getSIZE() / 2);
                head.setY(yCoordinate - this.graph.getSIZE() / 2);
            }
            this.updateVertexCoordinates(head.getAdjacentVertex(), key, xCoordinate, yCoordinate);
            head = head.getNextVertex();
        }
    }

    public void updateVertexCoordinates(Vertex vertex, String key, int  xCoordinate, int yCoordinate) {
        while (vertex != null) {
            if (vertex.getKey().equals(key)) {
                vertex.setX( xCoordinate);
                vertex.setY(yCoordinate);
            }
            vertex = vertex.getNextVertex();
        }
    }

    public int getAverageDistance(int xPoint, int yPoint) {
        return (xPoint + ((xPoint - yPoint) / 2) * -1);
    }

    public void mousePressed(MouseEvent e) {
        // TODO document why this method is empty
    }

    public void mouseMoved(MouseEvent e) {
        // TODO document why this method is empty
    }

    public void mouseEntered(MouseEvent e) {
        // TODO document why this method is empty
    }

    public void mouseExited(MouseEvent e) {
        // TODO document why this method is empty
    }


}
