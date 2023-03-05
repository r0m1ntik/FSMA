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

public class AttenteAttribution  extends Behaviour {

    private final Buyer buyerAgent;
    private final Model.Buyer buyerModel;

    public AttenteAttribution(Buyer buyerAgent, Model.Buyer buyerModel) {
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
        if(this.buyerModel.is_buyerAttrLot()) {
            //this.buyerAgent.addBehaviour(new Paiement(this.buyerAgent, this.buyerModel)); TODO
            return true;
        } else if (this.buyerModel.is_buyerAnnUpdated()) {
            this.buyerModel.set_buyerAnnUpdated(false);
            this.buyerModel.get_buyerUi().setEnableBoutonPropose(true);
            // this.buyerAgent.addBehaviour(new AttenteReponseOffre(this.buyerAgent, this.buyerModel)); TODO
            return true;
        }else {
            return false;
        }
    }
}
