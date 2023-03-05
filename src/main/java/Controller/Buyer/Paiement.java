/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Controller.Buyer;

import View.Buyer;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Paiement extends Behaviour {

    private final Buyer buyerAgent;
    private final Model.Buyer buyerModel;

    public Paiement(Buyer buyerAgent, Model.Buyer buyerModel) {
        super();
        this.buyerAgent = buyerAgent;
        this.buyerModel = buyerModel;
    }

    @Override
    public void action() {
        for (int i = 0; i < this.buyerModel.get_ventes().size(); i++) {
            if (this.buyerModel.get_ventes().get(i).get(3).equals("Vous avez la meilleure offre, vous avez gagné l'enchère !")){
                this.buyerModel.set_buyerMsg(this.buyerModel.get_ventes().get(i).get(0));
            }
        }
        // acheteur qui a remporte la vente
        this.buyerAgent.envoiMessage(this.buyerModel.get_buyerMsg(), "To_Pay", ACLMessage.CONFIRM);
    }

    @Override
    public boolean done() {
        return false;
    }
}
