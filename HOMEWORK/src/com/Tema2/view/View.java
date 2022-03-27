package com.Tema2.view;

import com.Tema2.controller.Draw;
import com.Tema2.controller.Graph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class View extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    Draw drawing;
    Graph graph;
    JMenuBar menuBar;
    JMenu graphMenu;
    JMenuItem edge, vertex, drag, delete;

    public View(String title) {

        this.setTitle(title);
        this.graph = new Graph();
        this.drawing = new Draw();

        menuBar = new JMenuBar();
        edge = new JMenuItem("Add edges");
        vertex = new JMenuItem("Add vertices");
        drag = new JMenuItem("Drag vertices");
        delete = new JMenuItem("Delete vertices");

        graphMenu = new JMenu("Menu");
        graphMenu.setLayout(new GridLayout(0, 1));
        graphMenu.add(vertex);
        graphMenu.add(edge);
        graphMenu.add(drag);
        graphMenu.add(delete);
        menuBar.add(graphMenu);

        edge.addActionListener(this);
        vertex.addActionListener(this);
        drag.addActionListener(this);
        delete.addActionListener(this);

        this.add(menuBar, BorderLayout.NORTH);

        drawing.setVisible(true);
        this.add(drawing);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        drawing.setSize(width, height);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) (width - width / 5), (int) (height - height / 10));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vertex) {
            drawing.setAction("Vertex");
        } else if (e.getSource() == this.edge) {
            drawing.setAction("Edge");
        } else if (e.getSource() == this.drag) {
            drawing.setAction("Drag");
        } else if (e.getSource() == this.delete) {
            drawing.setAction("Delete");
        }
    }

}
