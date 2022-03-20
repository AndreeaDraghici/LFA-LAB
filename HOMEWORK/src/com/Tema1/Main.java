package com.Tema1;

import javax.swing.*;
import java.awt.*;

/**
 * author: andreea draghici
 * 2022
 * Regex , LFA LAB
 */

public class Main {

    /**
     * Launch the application.
     */

    public static void main(String[] args) {

        try {
            Controller window = new Controller();
            window.frame.setVisible(true);
            window.frame.setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
