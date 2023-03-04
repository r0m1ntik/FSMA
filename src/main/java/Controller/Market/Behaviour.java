/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Controller.Market;

import View.Market;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.Vector;

public class Behaviour extends CyclicBehaviour {
    private final Market marketAgent;
    private final Model.Market marketModel;


    // vMarket herite de agent donc je peut l'utilisé ici en tant que agent
    public Behaviour(Market marketAgent, Model.Market model) {
        super();
        this.marketAgent = marketAgent;
        this.marketModel = model;
    }

    @Override
    public void action() {
        ACLMessage msg = marketAgent.receive();
        System.out.println("[Market -> Behaviour]: action(): " + msg);
        if (msg == null) {
            marketAgent.doWait();
            return;
        }

        String messageRecu = msg.getContent();
        int performative = msg.getPerformative();
        if (performative == ACLMessage.CFP) {
            traiterCFP(messageRecu);
        } else if (performative == ACLMessage.SUBSCRIBE) {
            traiterSubscribe(messageRecu);
        }
    }

    private void traiterCFP(String messageRecu) {
        String[] champsMsg = messageRecu.split(",");
        int positionExistant = getPositionAnnonce(champsMsg[0]);

        if (positionExistant != -1) {
            this.marketModel.get_marketListOffres().set(positionExistant, new Vector<>(Arrays.asList(champsMsg)));
            this.marketModel.get_MarketUi().majTable(positionExistant, this.marketModel.get_marketListOffres().get(positionExistant));
        } else {
            this.marketModel.get_marketListOffres().add(new Vector<>(Arrays.asList(champsMsg)));
            this.marketModel.get_MarketUi().ajoutNewTable(new Vector<>(Arrays.asList(champsMsg)));
        }

        envoyerMessagePreneurs(messageRecu);
    }

    private void traiterSubscribe(String messageRecu) {
        if (this.marketModel.get_marketListAbonnementBuyer().contains(messageRecu)) {
            return;
        }

        this.marketModel.get_marketListAbonnementBuyer().add(messageRecu);

        if (this.marketModel.get_marketListOffres().isEmpty()) {
            return;
        }

        String messageVentes = buildMessageVentes();
        this.marketAgent.envoiMessage(messageRecu, messageVentes);
    }

    private int getPositionAnnonce(String nomAnnonce) {
        for (int i = 0; i < this.marketModel.get_marketListOffres().size(); i++) {
            if (this.marketModel.get_marketListOffres().get(i).get(0).equals(nomAnnonce)) {
                return i;
            }
        }

        return -1;
    }

    private String buildMessageVentes() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.marketModel.get_marketListOffres().size(); i++) {
            Vector<String> annonce = this.marketModel.get_marketListOffres().get(i);

            sb.append(annonce.get(0)).append(",")
                    .append(annonce.get(1)).append(",")
                    .append(annonce.get(2)).append(",")
                    .append(annonce.get(3));

            if (i < this.marketModel.get_marketListOffres().size() - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    private void envoyerMessagePreneurs(String message) {
        for (String preneur : this.marketModel.get_marketListAbonnementBuyer()) {
            this.marketAgent.envoiMessage(preneur, message);
        }
    }
}
