/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import UI.BuyerUI;
import jade.core.*;

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
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }
}
