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

public class Initialisation extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;

    private final UI.SellerUI sellerUI;

    // On crée un constructeur pour initialiser la variable vendeurAgent
    public Initialisation(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
        super();
        this.sellerAgent = sellerAgent;
        this.sellerModel = sellerModel;
        this.sellerUI = sellerUI;
    }

    @Override
    public void action() {
        // L'agent vendeur attend une action
        this.sellerAgent.doWait();
        this.sellerUI.setTempsRestant(String.valueOf(this.sellerModel.get_timer()));
    }

    @Override
    public boolean done() {
        // Si l'état initial de l'agent vendeur est terminé
        if (this.sellerModel.is_etatInitialisationTermine()){
            // On réinitialise le montant à payer par l'acheteur à 0
            this.sellerModel.set_paiementAcheteur(0);
            // On met à jour le temps de repos de l'agent vendeur avec le temps actuel
            this.sellerModel.set_tempsRestant(this.sellerModel.get_timer());

            // On ajoute un comportement pour attendre la première offre de l'acheteur
            this.sellerAgent.addBehaviour(new AttentePremiereOffre(this.sellerAgent, this.sellerModel, this.sellerUI));

            // On crée un message pour informer le marché de la vente
            String[] parts = this.sellerModel.get_nomAgentVendeur().split("@");
            String messageVente = (parts[0] + "," + this.sellerModel.get_nomArticle() + "," + this.sellerModel.get_prixActuel()
                    + "," + this.sellerModel.get_statutEnchere());

            // On envoie le message au marché avec le protocole CFP
            this.sellerAgent.envoiMessage("Marche", messageVente, ACLMessage.CFP);
        }
        // On retourne vrai si l'état initial de l'agent vendeur est terminé, sinon on retourne faux
        return (this.sellerModel.is_etatEnchereTermine());
    }
}
