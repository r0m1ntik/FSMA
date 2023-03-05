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

import java.util.Arrays;
import java.util.Vector;

public class Seller extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");

        // Initialisation des constructeur de Model et UI
        Model.Seller mSeller = new Model.Seller();

        // On donne un nom au market
        mSeller.set_nomAgentVendeur(getAID().getName());
        mSeller.set_etatEnchereTermine(false);
        mSeller.set_etatInitialisationTermine(false);
        mSeller.set_paiementRecu(false);
        mSeller.set_annonceFinEnchereEffectuee(false);

        SellerUI uiSeller = new SellerUI(mSeller, this);
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

    public void ReceiveMsg(Model.Seller sellerModel) {
        String msgR;
        ACLMessage msg = receive();
        if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE) {
            msgR = msg.getContent();
            boolean exist = false;
            int positionExistant = 0;
            sellerModel.set_paiementAcheteur(sellerModel.get_paiementAcheteur() + 1);
            for (int i = 0; i < sellerModel.get_donnees().size(); i++) {
                if (sellerModel.get_donnees().get(i).get(0).equals(msgR)) {
                    exist = true;
                    positionExistant = i;
                    break;
                }
            }
            if (exist){
                System.out.println("exist");
                sellerModel.get_donnees().set(positionExistant, new Vector<>(Arrays.asList(msgR, "A propose")));
                sellerModel.get_sellerUi().updateTableAcheteur(positionExistant, sellerModel.get_donnees().get(positionExistant));
            } else {
                System.out.println("not exist");
                sellerModel.get_donnees().add(new Vector<>(Arrays.asList(msgR,"A propose")));
                sellerModel.get_sellerUi().addTableAcheteur(new Vector<>(Arrays.asList(msgR,"A propose")));
            }
        } else if (msg != null && msg.getPerformative() == ACLMessage.CONFIRM) {
            sellerModel.set_paiementRecu(true);
        }
    }
}
