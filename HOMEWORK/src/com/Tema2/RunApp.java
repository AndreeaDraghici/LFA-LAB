package com.Tema2;

import com.Tema2.controller.Draw;
import com.Tema2.view.View;

public class RunApp {

    public static void main(String[] args) {

        View view = new View("Graph Editor");
        view.add(new Draw());
    }
}
