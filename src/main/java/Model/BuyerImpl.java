/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Model;

import UI.BuyerUI;

import java.util.Vector;

public class BuyerImpl extends Buyer {

    public BuyerImpl(BuyerUI _buyerUi, Vector<Vector<String>> _ventes, String _buyerName, String _buyerType, int _buyerBudget, String _buyerMsg, boolean _buyerMode, int[] _buyerAnnounces, boolean _buyerProposition, boolean _buyerAbonne, boolean _buyerAbonneEmpty, boolean _buyerAnnonceSelecte, boolean _buyerInitEnd, boolean _buyerReceivGiveEnd, boolean _buyerAttrLot, boolean _buyerNonPropositonEnd, boolean _buyerAnnUpdated) {
        super(_buyerUi, _ventes, _buyerName, _buyerType, _buyerBudget, _buyerMsg, _buyerMode, _buyerAnnounces, _buyerProposition, _buyerAbonne, _buyerAbonneEmpty, _buyerAnnonceSelecte, _buyerInitEnd, _buyerReceivGiveEnd, _buyerAttrLot, _buyerNonPropositonEnd, _buyerAnnUpdated);
    }
}
