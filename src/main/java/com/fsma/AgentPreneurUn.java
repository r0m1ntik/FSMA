package com.fsma;

import jade.core.Agent;
import model.WindowModel;

public class AgentPreneurUn extends Agent {
    @Override
    protected void setup() {
        System.out.println("Agent "+getAID().getName()+" est prêt.");
        /* Configuration de la fenêtre */
        new WindowModel(600, 250, getAID().getName(), 160, 590);
    }

    @Override
    protected void takeDown() {
        System.out.println("Mon agent "+getAID().getName()+" est terminé.");
    }
}
