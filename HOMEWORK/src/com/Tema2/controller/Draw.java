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

public class Draw extends JPanel implements MouseListener, MouseMotionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    private String action;
    private String info;
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
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.drawVertices(g);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString(this.action, 10, 25);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString(info, 10, 40);
    }

    //display the info depending on the action selected and update current action
    public void setAction(String action) {
        this.action = action;
        if ("Vertex".equals(action)) {
            this.resetVerticesColors();
            this.info = "Click on the screen to add vertices";
        } else if ("Edge".equals(action)) {
            this.resetVerticesColors();
            this.info = "Click on vertices to join them";
        } else if ("Drag".equals(action)) {
            this.info = "Click on a vertex and drag it across the screen";
        } else if ("Delete".equals(action)) {
            this.info = "Click on a vertex to remove it";
        }
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if (this.action.equals("Drag")) {
            this.resetVerticesColors();
            this.currentVertex = "";
        }
    }

    // search for the vertex by coordinates and update them
    public void mouseDragged(MouseEvent e) {

        if (this.action.equals("Drag")) {
            if (this.currentVertex.equals("")) {
                String foundVertexKey = this.searchByCoordinates(e.getX(), e.getY());
                if (foundVertexKey == null) {
                    return;
                }
                this.currentVertex = foundVertexKey;
            }
            this.updateCoordinates(this.currentVertex, e.getX(), e.getY());
            this.repaint();
        }
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

    public void drawVertices(Graphics g) {

        Vertex vertex = this.graph.getHead();
        if (vertex == null) {
            return;
        }
        this.drawEdges(g);
        while (vertex != null) {
            g.setColor(vertex.getColor());
            g.fillOval(vertex.getX(), vertex.getY(), vertex.getZ(), vertex.getZ());
            g.setColor(Color.BLACK);
            g.drawString(vertex.getKey(), vertex.getX(), vertex.getY() - 10);
            vertex = vertex.getNext();
        }
    }

    private void drawEdges(Graphics g) {

        Vertex head = this.graph.getHead();
        if (head == null) {
            return;
        }
        Vertex vertex;
        g.setColor(Color.BLACK);
        while (head != null) {
            vertex = head.getAdjacentVertex();
            while (vertex != null && graph.getVertexMap().containsKey(vertex.getKey())) {
                g.drawString(vertex.getEdgeKey(), this.getAverageDistance(head.getX(), vertex.getX()), this.getAverageDistance(head.getY(), vertex.getY()));
                g.drawLine(vertex.getX(), vertex.getY(), head.getX() + (this.graph.getSIZE() / 2), head.getY() + (this.graph.getSIZE() / 2));
                vertex = vertex.getNext();
            }
            head = head.getNext();
        }
    }

    // searches a vertex by its coordinates and returns its key if found
    public String searchByCoordinates(int x, int y) {
        Vertex head = this.graph.getHead();
        if (head == null) {
            return null;
        }
        if (x > head.getX() && x < head.getX() + this.graph.getSIZE() && y > head.getY() && y < head.getY() + this.graph.getSIZE()) {
            head.setColor(Color.RED);
            return head.getKey();
        } else {
            head = head.getNext();
        }
        while (head != null) {
            if (x > head.getX() && x < head.getX() + this.graph.getSIZE() && y > head.getY() && y < head.getY() + this.graph.getSIZE()) {
                head.setColor(Color.RED);
                return head.getKey();
            } else {
                head = head.getNext();
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
            head.setColor(new Color(217, 130, 15));
            head = head.getNext();
        }
    }

    public void resetColor(String key) {
        if (this.graph.getVertexMap().containsKey(key)) {
            Vertex node = this.graph.getVertexMap().get(key);
            node.setColor(new Color(217, 130, 15));
            this.repaint();
        }
    }

    public void updateCoordinates(String key, int x, int y) {
        Vertex head = this.graph.getHead();
        while (head != null) {
            if (head.getKey().equals(key)) {
                head.setX(x - this.graph.getSIZE() / 2);
                head.setY(y - this.graph.getSIZE() / 2);
            }
            this.updateVertexCoordinates(head.getAdjacentVertex(), key, x, y);
            head = head.getNext();
        }
    }

    public void updateVertexCoordinates(Vertex vertex, String key, int x, int y) {
        while (vertex != null) {
            if (vertex.getKey().equals(key)) {
                vertex.setX(x);
                vertex.setY(y);
            }
            vertex = vertex.getNext();
        }
    }

    public int getAverageDistance(int px, int py) {
        return (px + ((px - py) / 2) * -1);
    }
}
