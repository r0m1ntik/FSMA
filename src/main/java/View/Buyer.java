/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import Controller.Buyer.TraitementAnnonces;
import UI.BuyerUI;
import jade.core.*;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.Vector;

public class Buyer extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");

        // Initialisation des constructeur de Model et UI
        Model.Buyer mBuyer = new Model.Buyer();
        // On donne un nom au Preneur
        mBuyer.set_buyerName(getAID().getName());

        BuyerUI uiBuyer = new BuyerUI(mBuyer);
        // On attribue une UI au modele
        mBuyer.set_buyerUi(uiBuyer);

        // Initialisation du controleur pour le marché
        TraitementAnnonces buyerTraitementAnnounce = new TraitementAnnonces(this, mBuyer);
        addBehaviour(buyerTraitementAnnounce);
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

    public void receiveMsg(Buyer buyerAgent, Model.Buyer buyerModel){
        String messageRecu;
        ACLMessage msg = receive();
        if (msg != null && msg.getPerformative() == ACLMessage.PROPAGATE) {
            messageRecu = msg.getContent();
            boolean existe = false;
            int positionExistant = 0;
            String[] champsMsg = messageRecu.split(",");
            for (int i = 0; i < buyerModel.get_ventes().size(); i++) {
                if (buyerModel.get_ventes().get(i).get(0).equals(champsMsg[0])) {
                    existe = true;
                    positionExistant = i;
                    break;
                }
            }
            if(existe){
                buyerModel.get_ventes().set(positionExistant, new Vector<>(Arrays.asList(champsMsg)));
                buyerModel.get_buyerUi().updateTableVente(positionExistant, buyerModel.get_ventes().get(positionExistant));
                if (champsMsg[3].equals("Ouvert")){
                    buyerModel.set_buyerAnnUpdated(true);
                }
            }else if(!buyerModel.is_buyerInitEnd()){
                buyerModel.get_ventes().add(new Vector<>(Arrays.asList(champsMsg)));
                buyerModel.get_buyerUi().addTableVente(new Vector<>(Arrays.asList(champsMsg)));
                buyerModel.set_buyerAbonneEmpty(false);
            }
        } else if (msg != null && msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
            buyerModel.set_buyerMsg(msg.getContent());
            buyerModel.set_buyerAttrLot(true);
        } else if (msg != null && msg.getPerformative() == ACLMessage.AGREE) {
            buyerModel.set_buyerReceivGiveEnd(true);
        } else {
            buyerAgent.doWait();
        }
    }
}
