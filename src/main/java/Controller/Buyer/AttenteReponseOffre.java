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

import java.util.Iterator;
import java.util.Vector;

public class AttenteReponseOffre extends Behaviour {

    private final Buyer buyerAgent;
    private final Model.Buyer buyerModel;

    public AttenteReponseOffre(Buyer buyerAgent, Model.Buyer buyerModel) {
        super();
        this.buyerAgent = buyerAgent;
        this.buyerModel = buyerModel;
    }

    @Override
    public void action() {
        // Stocker la valeur de retour de get_typeEnchere() dans une variable locale
        String typeEnchere = this.buyerModel.get_buyerType();

        // Section AUTO
        if (typeEnchere.equals("AUTO")){
            // Désactiver le bouton "Enchérir" dans l'interface utilisateur
            this.buyerModel.get_buyerUi().setEnableBoutonPropose(false);

            // Utiliser un itérateur pour parcourir la liste
            Iterator<Vector<String>> it = this.buyerModel.get_ventes().iterator();
            int index = 0;
            while (it.hasNext()) {
                Vector<String> data = it.next();
                if (Integer.parseInt(data.get(2)) <= this.buyerModel.get_buyerBudget() && (data.get(3).equals("Ouvert"))){
                    String nomVendeur = data.get(0);
                    data.set(3,"Propose");
                    this.buyerModel.get_buyerUi().updateTableVente(index, data);
                    this.buyerAgent.envoiMessage(nomVendeur, this.buyerModel.get_buyerName(), ACLMessage.PROPOSE);
                    this.buyerModel.set_buyerNonPropositonEnd(true);
                }
                index++;
            }
        } else if (typeEnchere.equals("MANUEL")) {
            if (this.buyerModel.is_buyerProposition()) {
                this.buyerModel.set_buyerProposition(false);
                int[] selectionPropositions = this.buyerModel.get_buyerUi().getSelectedRows();
                int j = 0;
                while (j < selectionPropositions.length) {
                    Vector<String> data = this.buyerModel.get_ventes().get(selectionPropositions[j]);
                    if (data.get(3).equals("Ouvert")) {
                        String nomVendeur = data.get(0);
                        data.set(3, "Propose");
                        this.buyerModel.get_ventes().set(selectionPropositions[j], data);
                        this.buyerModel.get_buyerUi().updateTableVente(selectionPropositions[j], data);
                        this.buyerAgent.envoiMessage(nomVendeur, this.buyerModel.get_buyerName(), ACLMessage.PROPOSE);
                        this.buyerModel.set_buyerNonPropositonEnd(true);
                        this.buyerModel.get_buyerUi().setEnableBoutonPropose(false);
                        this.buyerModel.get_buyerUi().unSelectAll();
                    }
                    j++;
                }
            }
        }

        this.buyerAgent.receiveMsg(this.buyerAgent, this.buyerModel);
    }

    @Override
    public boolean done() {
        if(this.buyerModel.is_buyerProposition()) {
            //this.buyerAgent.addBehaviour(new Paiement(this.buyerAgent, this.buyerModel)); TODO
            return true;
        } else if (this.buyerModel.is_buyerAnnUpdated()){
            this.buyerModel.set_buyerAnnUpdated(false);
            this.buyerModel.set_buyerNonPropositonEnd(false);
            this.buyerModel.get_buyerUi().setEnableBoutonPropose(true);
            //this.buyerAgent.addBehaviour(new AttenteReponseOffre(this.buyerAgent, this.buyerModel)); TODO
            return true;
        } else if (this.buyerModel.is_buyerNonPropositonEnd()){
            this.buyerModel.set_buyerNonPropositonEnd(false);
            //this.buyerAgent.addBehaviour(new AttenteAttribution(this.buyerAgent, this.buyerModel)); TODO
            return true;
        }else
            return false;
    }
}
