package model;

import javax.swing.*;

public class WindowModel {

    public WindowModel(Integer width, Integer height, String name, int positionX, int positionY) {
        /* Configuration de la fenêtre */
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(name);
        frame.setResizable(false);
        frame.setLocation(positionX, positionY);
        frame.setVisible(true);
        /* on ferme la fenêtre et on stop l'agent */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
