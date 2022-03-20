package com.Tema1;

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

        EventQueue.invokeLater(() -> {
            try {
                Controller window = new Controller();
                window.frame.setVisible(true);
                window.frame.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
