package com.fsma;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import model.WindowModel;

public class AgentMarche extends Agent {

    @Override
    protected void setup() {
        System.out.println("Agent "+getAID().getName()+" est prêt.");
        
        /* Configuration de la fenêtre */
        new WindowModel(600, 250, getAID().getName(), 475, 325);

        /* Création d'une instance "Runtime" de l'architecture JADE */
        Runtime runtime = Runtime.instance();

        /* Création d'un objet "Profile" pour définir le contexte */
        Profile profile = new ProfileImpl();

        /* Définition du hôte principal (localhost) */
        profile.setParameter(Profile.MAIN_HOST, "localhost");

        /* Activation de l'interface graphique JADE */
        profile.setParameter(Profile.GUI, "true");

        /* Création d'un nouveau contrôleur de conteneur pour le profil fourni */
        ContainerController containerController = runtime.createMainContainer(profile);

        /* Declaration des agents (v pour vendeur, p pour preneur) */
        AgentController v1, v2, p1, p2;

        // On declare dynamiquement nos Agents, pour ne pas le declarer dans les arguments
        try {
            v1 = containerController.createNewAgent("seller1", "com.fsma.AgentVendeurUn", null);
            v2 = containerController.createNewAgent("seller2", "com.fsma.AgentVendeurDeux", null);
            p1 = containerController.createNewAgent("buyer1", "com.fsma.AgentPreneurUn", null);
            p2 = containerController.createNewAgent("buyer2", "com.fsma.AgentPreneurDeux", null);

            // Ouverture des agents
            v1.start();
            v2.start();
            p1.start();
            p2.start();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Mon agent "+getAID().getName()+" est terminé.");
    }
}
