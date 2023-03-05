/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Controller.Buyer;

import Model.Market;
import View.Buyer;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.Vector;

public class TraitementAnnonces extends Behaviour {

    private final Buyer buyerAgent;
    private final Model.Buyer buyerModel;

    public TraitementAnnonces(Buyer buyerAgent, Model.Buyer buyerModel) {
        super();
        this.buyerAgent = buyerAgent;
        this.buyerModel = buyerModel;
    }

    @Override
    public void action() {
        System.out.println("[Buyer -> TraitementAnnounces]: action(): " + this.buyerModel.is_buyerAbonne());
        if(!this.buyerModel.is_buyerAbonne()){
            this.buyerModel.set_buyerAbonne(true);
            String[] parts = this.buyerModel.get_buyerName().split("@");
            this.buyerAgent.envoiMessage("Marche", parts[0], ACLMessage.SUBSCRIBE);
        }
        this.buyerAgent.receiveMsg(this.buyerAgent, this.buyerModel);
    }

    @Override
    public boolean done() {
        System.out.println("[Buyer -> TraitementAnnounces]: done(): " + this.buyerModel.is_buyerAbonne());
        if (!this.buyerModel.is_buyerAbonneEmpty() && this.buyerModel.is_buyerMode() && this.buyerModel.is_buyerAnnonceSelecte()){
            this.buyerModel.get_buyerUi().setStatut("Abonnement OK");
            for (int i = 0; i < this.buyerModel.get_buyerAnnounces().length; i++ ){
                Vector<String> data = this.buyerModel.get_ventes().get(this.buyerModel.get_buyerAnnounces()[i]);
                if(data.get(3).equals("Ouvert")){
                    data.set(3,"Propose");
                    this.buyerModel.get_ventes().set(this.buyerModel.get_buyerAnnounces()[i], data);
                    String[] parts = this.buyerModel.get_buyerName().split("@");
                    this.buyerAgent.envoiMessage(data.get(0), parts[0], ACLMessage.PROPOSE);
                }
            }
            System.out.println("data(3): " + this.buyerModel.get_buyerAnnounces().length);
            int positionVecteur = 0;
            int tailleVecteurOriginale = this.buyerModel.get_ventes().size();
            for (int i = 0; i< tailleVecteurOriginale; i++ ){
                Vector<String> data = this.buyerModel.get_ventes().get(positionVecteur);
                if (!data.get(3).equals("Propose")){
                    this.buyerModel.get_ventes().remove(positionVecteur);
                }else{
                    positionVecteur++;
                }
            }
            this.buyerModel.get_buyerUi().setEnableBoutonPropose(false);
            this.buyerModel.get_buyerUi().unSelectAll();
            this.buyerModel.get_buyerUi().resetRowEnchere(this.buyerModel.get_ventes());
            if (this.buyerModel.get_ventes().size() == 0) {
                this.buyerModel.get_buyerUi().setStatut("Il n'y a plus d'enchères disponibles.");
                this.buyerModel.doDelete();
            }
            this.buyerAgent.addBehaviour(new AttenteAttribution(this.buyerAgent, this.buyerModel));
            this.buyerModel.set_buyerInitEnd(true);
            return true;
        }
        return (this.buyerModel.is_buyerInitEnd());
    }
}
