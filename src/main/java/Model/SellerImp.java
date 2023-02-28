/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package Model;

import UI.SellerUI;

public class SellerImp extends Seller {

    public SellerImp(SellerUI _sellerUi, boolean _etatInitialisationTermine, boolean _annonceFinEnchereEffectuee, int _tempsRestant, int _incrementation, int _decrementation, String _nomAgentVendeur, String _nomArticle, String _statutEnchere, String _acheteurGagnant, boolean _etatEnchereTermine, boolean _paiementRecu, int _paiementAcheteur, int _prixActuel, int _timer) {
        super(_sellerUi, _etatInitialisationTermine, _annonceFinEnchereEffectuee, _tempsRestant, _incrementation, _decrementation, _nomAgentVendeur, _nomArticle, _statutEnchere, _acheteurGagnant, _etatEnchereTermine, _paiementRecu, _paiementAcheteur, _prixActuel, _timer);
    }
}
