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

import java.util.Arrays;
import java.util.Vector;

public class Attribution extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;
    private final UI.SellerUI sellerUI;

    public Attribution(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
        super();
        this.sellerAgent = sellerAgent;
        this.sellerModel = sellerModel;
        this.sellerUI = sellerUI;
    }

    @Override
    public void action() {
        for (int i = 0; i < this.sellerModel.get_donnees().size(); i++){
            if (this.sellerModel.get_donnees().get(i).get(1).equals("A propose")){
                this.sellerModel.set_acheteurGagnant(this.sellerModel.get_donnees().get(i).get(0));
            }
        }
        String[] parts = this.sellerModel.get_nomAgentVendeur().split("@");
        this.sellerAgent.envoiMessage(this.sellerModel.get_acheteurGagnant(), parts[0], ACLMessage.ACCEPT_PROPOSAL);
        this.sellerModel.get_sellerUi().setStatut("Paiement recu. Enchere termine !");
        this.sellerModel.get_sellerUi().finEnchere(this.sellerModel.get_acheteurGagnant());
        for (int j = 0; j < this.sellerModel.get_donnees().size(); j++) {
            if (this.sellerModel.get_donnees().get(j).get(0).equals(this.sellerModel.get_acheteurGagnant())) {
                this.sellerModel.get_donnees().set(j, new Vector<>(Arrays.asList(this.sellerModel.get_acheteurGagnant(), "A remporte l'enchere!")));
                this.sellerModel.get_sellerUi().updateTableAcheteur(j, this.sellerModel.get_donnees().get(j));
            }else{
                this.sellerModel.get_donnees().set(j, new Vector<>(Arrays.asList(this.sellerModel.get_donnees().get(j).get(0), "A perdu l'enchere")));
                this.sellerModel.get_sellerUi().updateTableAcheteur(j, this.sellerModel.get_donnees().get(j));
            }
        }        
    }

    @Override
    public boolean done() {
        this.sellerAgent.addBehaviour(new AttentePaiement(this.sellerAgent, this.sellerModel, this.sellerUI));
        return true;
    }
}
