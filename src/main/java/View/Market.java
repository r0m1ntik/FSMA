/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import UI.MarketUI;
import jade.core.Agent;

public class Market extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");

        // Initialisation des constructeur de Model et UI
        Model.Market mMarket = new Model.Market();

        // On donne un nom au market
        mMarket.set_MarketName(getAID().getName());

        MarketUI uiMarket = new MarketUI(mMarket);

        // On attribue une UI au modele
        mMarket.set_MarketUi(uiMarket);
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }
}
