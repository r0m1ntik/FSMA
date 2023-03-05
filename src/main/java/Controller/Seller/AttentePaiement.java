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

public class AttentePaiement extends Behaviour {

    // On crée une variable pour stocker l'agent vendeur
    private final Seller sellerAgent;
    private final Model.Seller sellerModel;
    private final UI.SellerUI sellerUI;

    public AttentePaiement(Seller sellerAgent, Model.Seller sellerModel, UI.SellerUI sellerUI) {
        super();
        this.sellerAgent = sellerAgent;
        this.sellerModel = sellerModel;
        this.sellerUI = sellerUI;
    }

    @Override
    public void action() {
        this.sellerAgent.ReceiveMsg(this.sellerModel);
    }

    @Override
    public boolean done() {
        if (this.sellerModel.is_paiementRecu()){
            this.sellerAgent.addBehaviour(new Attribution(this.sellerAgent, this.sellerModel, this.sellerUI));
            return true;
        } else return false;
    }
}
