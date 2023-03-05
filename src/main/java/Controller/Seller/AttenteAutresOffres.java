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

public class AttenteAutresOffres extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;
    private final UI.SellerUI sellerUI;

    public AttenteAutresOffres(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
        super();
        this.sellerAgent = sellerAgent;
        this.sellerModel = sellerModel;
        this.sellerUI = sellerUI;
    }

    @Override
    public void action() {
        this.sellerAgent.ReceiveMsg(this.sellerModel);
        block(1000);
        this.sellerModel.set_tempsRestant(this.sellerModel.get_tempsRestant() - 1);
        this.sellerModel.get_sellerUi().setTempsRestant(String.valueOf(sellerModel.get_tempsRestant()));
        if (this.sellerModel.get_tempsRestant() <= 0){
            this.sellerModel.set_tempsRestant(this.sellerModel.get_timer());
            this.sellerModel.set_annonceFinEnchereEffectuee(true);
            this.sellerModel.get_sellerUi().setTempsRestant(String.valueOf(this.sellerModel.get_tempsRestant()));
        }
    }

    @Override
    public boolean done() {
        if (this.sellerModel.is_annonceFinEnchereEffectuee()) {
            this.sellerModel.set_prixActuel(this.sellerModel.get_incrementation() + this.sellerModel.get_prixActuel());
            this.sellerModel.get_sellerUi().setPrixActuelAffichage(this.sellerModel.get_prixActuel());
            this.sellerModel.get_sellerUi().resetStatutAcheteur();

            for(int i=0; i<this.sellerModel.get_donnees().size();i++) {
                this.sellerModel.get_donnees().get(i).set(1, "N'a pas propose");
            }
            this.sellerModel.set_annonceFinEnchereEffectuee(false);
            this.sellerModel.get_sellerUi().setStatut("Nouvelle annonce pour le lot : le prix a été révisé à la hausse.");

            String[] parts = this.sellerModel.get_nomAgentVendeur().split("@");
            String messageVente = (parts[0] + "," + this.sellerModel.get_nomArticle() + "," + this.sellerModel.get_prixActuel()
                    + "," + this.sellerModel.get_statutEnchere());

            this.sellerAgent.envoiMessage("Marche", messageVente, ACLMessage.CFP);
            this.sellerModel.set_paiementAcheteur(0);
            this.sellerAgent.addBehaviour(new AttentePremiereOffre(this.sellerAgent, this.sellerModel, this.sellerUI));
            return true;
        }else
            return false;
    }
}