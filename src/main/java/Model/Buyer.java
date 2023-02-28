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

public class Buyer {

    /* L'interface utilisateur pour preneur */
    private BuyerUI _buyerUi;

    /* Liste des ventes que le market lui envoie */
    private Vector<Vector<String>> _ventes;

    /* Nom du vendeur */
    private String _buyerName;

    /* Type d'achat du preneur */
    private String _buyerType;

    /* Stocke le budget du preneur */
    private int _buyerBudget;

    /* Message d'affichage pour le preneur */
    private String _buyerMsg;

    /* Mode selectionné par le preneur (auto ou manuel) */
    private boolean _buyerMode;

    /* Stocke les annonces sélectionnées par le preneur */
    private int[] _buyerAnnounces;

    /* Permet de déterminer si une proposition est correcte */
    private boolean _buyerProposition;

    /* Permet de savoir si le preneur est abonné */
    private boolean _buyerAbonne;

    /* Permet de déterminer si la liste des abonnés est vide */
    private boolean _buyerAbonneEmpty;

    /* Indique si le preneur a sélectionné des annonces */
    private boolean _buyerAnnonceSelecte;

    /* Permet de déterminer si l'état initial est terminé */
    private boolean _buyerInitEnd;

    /* permet de déterminer si l'état de réception/don est terminé */
    private boolean _buyerReceivGiveEnd;

    /* Permet de déterminer si le lot est attribué */
    private boolean _buyerAttrLot;

    /* Permet de déterminer si la non-proposition est terminée */
    private boolean _buyerNonPropositonEnd;

    /* Permet de déterminer si l'annonce a été mise à jour */
    private boolean _buyerAnnUpdated;

    public Buyer(BuyerUI _buyerUi, Vector<Vector<String>> _ventes, String _buyerName, String _buyerType, int _buyerBudget, String _buyerMsg, boolean _buyerMode, int[] _buyerAnnounces, boolean _buyerProposition, boolean _buyerAbonne, boolean _buyerAbonneEmpty, boolean _buyerAnnonceSelecte, boolean _buyerInitEnd, boolean _buyerReceivGiveEnd, boolean _buyerAttrLot, boolean _buyerNonPropositonEnd, boolean _buyerAnnUpdated) {
        this._buyerUi = _buyerUi;
        this._ventes = _ventes;
        this._buyerName = _buyerName;
        this._buyerType = _buyerType;
        this._buyerBudget = _buyerBudget;
        this._buyerMsg = _buyerMsg;
        this._buyerMode = _buyerMode;
        this._buyerAnnounces = _buyerAnnounces;
        this._buyerProposition = _buyerProposition;
        this._buyerAbonne = _buyerAbonne;
        this._buyerAbonneEmpty = _buyerAbonneEmpty;
        this._buyerAnnonceSelecte = _buyerAnnonceSelecte;
        this._buyerInitEnd = _buyerInitEnd;
        this._buyerReceivGiveEnd = _buyerReceivGiveEnd;
        this._buyerAttrLot = _buyerAttrLot;
        this._buyerNonPropositonEnd = _buyerNonPropositonEnd;
        this._buyerAnnUpdated = _buyerAnnUpdated;
    }

    public BuyerUI get_buyerUi() {
        return _buyerUi;
    }

    public void set_buyerUi(BuyerUI _buyerUi) {
        this._buyerUi = _buyerUi;
    }

    public Vector<Vector<String>> get_ventes() {
        return _ventes;
    }

    public void set_ventes(Vector<Vector<String>> _ventes) {
        this._ventes = _ventes;
    }

    public String get_buyerName() {
        return _buyerName;
    }

    public void set_buyerName(String _buyerName) {
        this._buyerName = _buyerName;
    }

    public String get_buyerType() {
        return _buyerType;
    }

    public void set_buyerType(String _buyerType) {
        this._buyerType = _buyerType;
    }

    public int get_buyerBudget() {
        return _buyerBudget;
    }

    public void set_buyerBudget(int _buyerBudget) {
        this._buyerBudget = _buyerBudget;
    }

    public String get_buyerMsg() {
        return _buyerMsg;
    }

    public void set_buyerMsg(String _buyerMsg) {
        this._buyerMsg = _buyerMsg;
    }

    public boolean is_buyerMode() {
        return _buyerMode;
    }

    public void set_buyerMode(boolean _buyerMode) {
        this._buyerMode = _buyerMode;
    }

    public int[] get_buyerAnnounces() {
        return _buyerAnnounces;
    }

    public void set_buyerAnnounces(int[] _buyerAnnounces) {
        this._buyerAnnounces = _buyerAnnounces;
    }

    public boolean is_buyerProposition() {
        return _buyerProposition;
    }

    public void set_buyerProposition(boolean _buyerProposition) {
        this._buyerProposition = _buyerProposition;
    }

    public boolean is_buyerAbonne() {
        return _buyerAbonne;
    }

    public void set_buyerAbonne(boolean _buyerAbonne) {
        this._buyerAbonne = _buyerAbonne;
    }

    public boolean is_buyerAbonneEmpty() {
        return _buyerAbonneEmpty;
    }

    public void set_buyerAbonneEmpty(boolean _buyerAbonneEmpty) {
        this._buyerAbonneEmpty = _buyerAbonneEmpty;
    }

    public boolean is_buyerAnnonceSelecte() {
        return _buyerAnnonceSelecte;
    }

    public void set_buyerAnnonceSelecte(boolean _buyerAnnonceSelecte) {
        this._buyerAnnonceSelecte = _buyerAnnonceSelecte;
    }

    public boolean is_buyerInitEnd() {
        return _buyerInitEnd;
    }

    public void set_buyerInitEnd(boolean _buyerInitEnd) {
        this._buyerInitEnd = _buyerInitEnd;
    }

    public boolean is_buyerReceivGiveEnd() {
        return _buyerReceivGiveEnd;
    }

    public void set_buyerReceivGiveEnd(boolean _buyerReceivGiveEnd) {
        this._buyerReceivGiveEnd = _buyerReceivGiveEnd;
    }

    public boolean is_buyerAttrLot() {
        return _buyerAttrLot;
    }

    public void set_buyerAttrLot(boolean _buyerAttrLot) {
        this._buyerAttrLot = _buyerAttrLot;
    }

    public boolean is_buyerNonPropositonEnd() {
        return _buyerNonPropositonEnd;
    }

    public void set_buyerNonPropositonEnd(boolean _buyerNonPropositonEnd) {
        this._buyerNonPropositonEnd = _buyerNonPropositonEnd;
    }

    public boolean is_buyerAnnUpdated() {
        return _buyerAnnUpdated;
    }

    public void set_buyerAnnUpdated(boolean _buyerAnnUpdated) {
        this._buyerAnnUpdated = _buyerAnnUpdated;
    }
}
