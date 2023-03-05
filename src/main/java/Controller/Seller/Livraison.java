/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Controller.Seller;

import View.Seller;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Livraison extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;

    private final UI.SellerUI sellerUI;

    // On crée un constructeur pour initialiser la variable vendeurAgent
    public Livraison(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
        super();
        this.sellerAgent = sellerAgent;
        this.sellerModel = sellerModel;
        this.sellerUI = sellerUI;
    }

    @Override
    public void action() {
        String[] parts = this.sellerModel.get_nomAgentVendeur().split("@");
        this.sellerAgent.envoiMessage(this.sellerModel.get_acheteurGagnant(), parts[0], ACLMessage.AGREE);
    }

    @Override
    public boolean done() {
        return true;
    }
}
