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

public class Market extends View.Market {

    /* Liste des offres des vendeurs */
    private Vector<Vector<String>> _marketListOffres;
    /* Liste des abonnements des Buyer */
    private Vector<String> _marketListAbonnementBuyer;

    /* L'interface utilisateur pour Market */
    private MarketUI _MarketUi;

    private String _MarketName;

    public Market(Vector<Vector<String>> _marketListOffres, Vector<String> _marketListAbonnementBuyer, MarketUI _MarketUi, String name) {
        this._marketListOffres = _marketListOffres;
        this._marketListAbonnementBuyer = _marketListAbonnementBuyer;
        this._MarketUi = _MarketUi;
        this._MarketName = name;
    }

    public Market() { }

    public String get_MarketName() {
        return _MarketName;
    }

    public void set_MarketName(String _MarketName) {
        this._MarketName = _MarketName;
    }

    public void set_MarketUi(MarketUI _MarketUi) {
        this._MarketUi = _MarketUi;
    }

    public Vector<Vector<String>> get_marketListOffres() {
        return _marketListOffres;
    }

    public void set_marketListOffres(Vector<Vector<String>> _marketListOffres) {
        this._marketListOffres = _marketListOffres;
    }

    public Vector<String> get_marketListAbonnementBuyer() {
        return _marketListAbonnementBuyer;
    }

    public void set_marketListAbonnementBuyer(Vector<String> _marketListAbonnementBuyer) {
        this._marketListAbonnementBuyer = _marketListAbonnementBuyer;
    }

    public MarketUI get_MarketUi() {
        return _MarketUi;
    }
}
