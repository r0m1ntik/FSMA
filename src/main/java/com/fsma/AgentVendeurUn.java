package com.fsma;

import jade.core.Agent;
import javax.swing.JFrame;

public class AgentVendeurUn extends Agent {

    @Override
    protected void setup() {
        System.out.println("Bonjour! Mon agent "+getLocalName()+" est prêt.");

        /* Configuration de la fenêtre */
        JFrame frame = new JFrame();
        frame.setSize(600, 250);
        frame.setTitle(getLocalName());
        frame.setResizable(false);
        frame.setLocation(15, 45);
        frame.setVisible(true);

        /* on ferme la fenêtre et on stop l'agent */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected void takeDown() {
        System.out.println("Mon agent "+getLocalName()+" est terminé.");
    }
}