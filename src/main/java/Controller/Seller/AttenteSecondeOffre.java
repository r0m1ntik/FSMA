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

public class AttenteSecondeOffre extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;

    private final UI.SellerUI sellerUI;

    // On crée un constructeur pour initialiser la variable vendeurAgent
    public AttenteSecondeOffre(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
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
            this.sellerModel.set_etatEnchereTermine(true);
            this.sellerModel.set_tempsRestant(this.sellerModel.get_timer());
        }
    }

    @Override
    public boolean done() {
        if (this.sellerModel.is_etatEnchereTermine()){
            this.sellerModel.set_etatEnchereTermine(false);
            this.sellerModel.get_sellerUi().setStatut("Lot emporte par: ");
            this.sellerModel.set_statutEnchere("La période d'enchère est terminée.");
            this.sellerModel.get_sellerUi().setStatutLot(this.sellerModel.get_statutEnchere());

            String[] parts = this.sellerModel.get_nomAgentVendeur().split("@");
            String messageVente = (parts[0] + "," + this.sellerModel.get_nomArticle() + "," + this.sellerModel.get_prixActuel()
                    + "," + this.sellerModel.get_statutEnchere());
            this.sellerAgent.envoiMessage("Marche", messageVente, ACLMessage.CFP);
            this.sellerAgent.addBehaviour(new Attribution(this.sellerAgent, this.sellerModel, this.sellerUI));
            return true;
        }else if(this.sellerModel.get_paiementAcheteur() >= 2){
            this.sellerAgent.addBehaviour(new AttenteAutresOffres(this.sellerAgent, this.sellerModel, this.sellerUI));
            return true;
        }else
            return false;
    }
}
