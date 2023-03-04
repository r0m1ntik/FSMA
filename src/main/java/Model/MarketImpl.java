/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Model;

import UI.MarketUI;
import java.util.Vector;

public class MarketImpl extends Market {
    public MarketImpl(Vector<Vector<String>> _marketListOffres, Vector<String> _marketListAbonnementBuyer, MarketUI _MarketUi, String _MarketName) {
        super(_marketListOffres, _marketListAbonnementBuyer, _MarketUi, _MarketName);
    }
}
