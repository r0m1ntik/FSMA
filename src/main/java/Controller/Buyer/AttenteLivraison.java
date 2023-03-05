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

public class AttenteLivraison extends Behaviour {

    private final Buyer buyerAgent;
    private final Model.Buyer buyerModel;

    public AttenteLivraison(Buyer buyerAgent, Model.Buyer buyerModel) {
        super();
        this.buyerAgent = buyerAgent;
        this.buyerModel = buyerModel;
    }

    @Override
    public void action() {
        this.buyerAgent.receiveMsg(this.buyerAgent, this.buyerModel);
    }

    @Override
    public boolean done() {
        if (this.buyerModel.is_buyerReceivGiveEnd()){
            for (int i = 0; i < this.buyerModel.get_ventes().size(); i++) {
                if (this.buyerModel.get_ventes().get(i).get(0).equals(this.buyerModel.get_buyerMsg())) {
                    this.buyerModel.get_ventes().get(i).set(3, "Vous avez la meilleure offre, vous avez gagné l'enchère !");
                }
            }
            this.buyerModel.get_buyerUi().setStatut("Vous avez la meilleure offre, vous avez gagné l'enchère !");
            this.buyerModel.get_buyerUi().setEnableBoutonPropose(false);
            return true;
        } else {
            return false;
        }
    }
}