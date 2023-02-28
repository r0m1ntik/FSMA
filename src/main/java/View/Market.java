/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package View;

import jade.core.Agent;

public class Market extends Agent {

    @Override
    protected void setup() {
        /* Message au debut */
        System.out.println("Agent "+getAID().getName()+" est prêt.");
    }

    @Override
    protected void takeDown() {
        /* Message à la fin */
        System.out.println("Agent "+getAID().getName()+" est terminé.");
    }
}
