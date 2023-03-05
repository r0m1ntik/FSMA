/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import Controller.Market.Behaviour;
import UI.MarketUI;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.Vector;

public class Market extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");

        // Initialisation des constructeur de Model et UI
        Model.Market mMarket = new Model.Market();

        // On donne un nom au market
        mMarket.set_MarketName(getAID().getName());
        // Initialisation des vecteurs (nullpointerexeption)
        mMarket.set_marketListAbonnementBuyer(new Vector<>());
        mMarket.set_marketListOffres(new Vector<>());

        MarketUI uiMarket = new MarketUI(mMarket);

        // On attribue une UI au modele
        mMarket.set_MarketUi(uiMarket);

        // Initialisation du controleur pour le marché
        Controller.Market.Behaviour marketBehaviour = new Behaviour(this, mMarket);
        addBehaviour(marketBehaviour);
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }

    public void envoiMessage(String receiver, String msg) {
        ACLMessage message = new ACLMessage(ACLMessage.PROPAGATE);
        message.addReceiver(new AID(receiver, AID.ISLOCALNAME));
        message.setLanguage("FishMarket");
        message.setOntology("FishSale-ontology");
        message.setContent(msg);
        send(message);
    }
}
