/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import Controller.Seller.Initialisation;
import UI.SellerUI;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Seller extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");

        // Initialisation des constructeur de Model et UI
        Model.Seller mSeller = new Model.Seller();

        // On donne un nom au market
        mSeller.set_nomAgentVendeur(getAID().getName());

        SellerUI uiSeller = new SellerUI(mSeller);

        // On attribue une UI au modele
        mSeller.set_sellerUi(uiSeller);

        // Initialisation du controleur pour le marché
        Initialisation sellerInit = new Initialisation(this, mSeller);
        addBehaviour(sellerInit);
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }

    public void envoiMessage(String receiver, String msg, int perf) {
        ACLMessage message = new ACLMessage(perf);
        message.addReceiver(new AID(receiver, AID.ISLOCALNAME));
        message.setLanguage("FishMarket");
        message.setOntology("FishSale-ontology");
        message.setContent(msg);
        send(message);
    }
}
