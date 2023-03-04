/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import UI.SellerUI;
import jade.core.Agent;

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
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }
}
