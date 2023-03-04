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

import java.util.Vector;

public class Seller extends View.Seller {

    /* Interface utilisateur pour le vendeur */
    private SellerUI _sellerUi;

    /* Données pour le vendeur */
    private Vector<Vector<String>> _donnees;

    /* Indicateur indiquant si l'état d'initialisation est terminé */
    private boolean _etatInitialisationTermine;

    /* Indicateur indiquant si l'annonce de la fin de l'enchère a été faite */
    private boolean _annonceFinEnchereEffectuee;

    /* Temps restant en secondes pour le tour d'enchères en cours */
    private int _tempsRestant;

    /* Valeur d'incrémentation pour augmenter le prix de l'enchère */
    private int _incrementation;

    /* Valeur de décrémentation pour diminuer le prix de l'enchère */
    private int _decrementation;

    /* Nom de l'agent vendeur */
    private String _nomAgentVendeur;

    /* Nom de l'article vendu par le vendeur */
    private String _nomArticle;

    /* Statut de l'enchère (par exemple "ouverte", "fermée", "vendue", etc.) */
    private String _statutEnchere;

    /* Nom de l'acheteur ayant remporté l'enchère */
    private String _acheteurGagnant;

    /* Indicateur indiquant si l'état d'enchères est terminé */
    private boolean _etatEnchereTermine;

    /* Indicateur indiquant si le paiement a été reçu par le vendeur */
    private boolean _paiementRecu;

    /* Montant que l'acheteur doit payer au vendeur */
    private int _paiementAcheteur;

    /* Prix actuel de l'article vendu par le vendeur */
    private int _prixActuel;

    /* Temps en secondes pour chaque tour d'enchères */
    private int _timer;

    public Seller(SellerUI _sellerUi, boolean _etatInitialisationTermine, boolean _annonceFinEnchereEffectuee, int _tempsRestant, int _incrementation, int _decrementation, String _nomAgentVendeur, String _nomArticle, String _statutEnchere, String _acheteurGagnant, boolean _etatEnchereTermine, boolean _paiementRecu, int _paiementAcheteur, int _prixActuel, int _timer) {
        this._sellerUi = _sellerUi;
        this._etatInitialisationTermine = _etatInitialisationTermine;
        this._annonceFinEnchereEffectuee = _annonceFinEnchereEffectuee;
        this._tempsRestant = _tempsRestant;
        this._incrementation = _incrementation;
        this._decrementation = _decrementation;
        this._nomAgentVendeur = _nomAgentVendeur;
        this._nomArticle = _nomArticle;
        this._statutEnchere = _statutEnchere;
        this._acheteurGagnant = _acheteurGagnant;
        this._etatEnchereTermine = _etatEnchereTermine;
        this._paiementRecu = _paiementRecu;
        this._paiementAcheteur = _paiementAcheteur;
        this._prixActuel = _prixActuel;
        this._timer = _timer;
    }

    public Seller() {

    }

    public SellerUI get_sellerUi() {
        return _sellerUi;
    }

    public void set_sellerUi(SellerUI _sellerUi) {
        this._sellerUi = _sellerUi;
    }

    public Vector<Vector<String>> get_donnees() {
        return _donnees;
    }

    public void set_donnees(Vector<Vector<String>> _donnees) {
        this._donnees = _donnees;
    }

    public boolean is_etatInitialisationTermine() {
        return _etatInitialisationTermine;
    }

    public void set_etatInitialisationTermine(boolean _etatInitialisationTermine) {
        this._etatInitialisationTermine = _etatInitialisationTermine;
    }

    public boolean is_annonceFinEnchereEffectuee() {
        return _annonceFinEnchereEffectuee;
    }

    public void set_annonceFinEnchereEffectuee(boolean _annonceFinEnchereEffectuee) {
        this._annonceFinEnchereEffectuee = _annonceFinEnchereEffectuee;
    }

    public int get_tempsRestant() {
        return _tempsRestant;
    }

    public void set_tempsRestant(int _tempsRestant) {
        this._tempsRestant = _tempsRestant;
    }

    public int get_incrementation() {
        return _incrementation;
    }

    public void set_incrementation(int _incrementation) {
        this._incrementation = _incrementation;
    }

    public int get_decrementation() {
        return _decrementation;
    }

    public void set_decrementation(int _decrementation) {
        this._decrementation = _decrementation;
    }

    public String get_nomAgentVendeur() {
        return _nomAgentVendeur;
    }

    public void set_nomAgentVendeur(String _nomAgentVendeur) {
        this._nomAgentVendeur = _nomAgentVendeur;
    }

    public String get_nomArticle() {
        return _nomArticle;
    }

    public void set_nomArticle(String _nomArticle) {
        this._nomArticle = _nomArticle;
    }

    public String get_statutEnchere() {
        return _statutEnchere;
    }

    public void set_statutEnchere(String _statutEnchere) {
        this._statutEnchere = _statutEnchere;
    }

    public String get_acheteurGagnant() {
        return _acheteurGagnant;
    }

    public void set_acheteurGagnant(String _acheteurGagnant) {
        this._acheteurGagnant = _acheteurGagnant;
    }

    public boolean is_etatEnchereTermine() {
        return _etatEnchereTermine;
    }

    public void set_etatEnchereTermine(boolean _etatEnchereTermine) {
        this._etatEnchereTermine = _etatEnchereTermine;
    }

    public boolean is_paiementRecu() {
        return _paiementRecu;
    }

    public void set_paiementRecu(boolean _paiementRecu) {
        this._paiementRecu = _paiementRecu;
    }

    public int get_paiementAcheteur() {
        return _paiementAcheteur;
    }

    public void set_paiementAcheteur(int _paiementAcheteur) {
        this._paiementAcheteur = _paiementAcheteur;
    }

    public int get_prixActuel() {
        return _prixActuel;
    }

    public void set_prixActuel(int _prixActuel) {
        this._prixActuel = _prixActuel;
    }

    public int get_timer() {
        return _timer;
    }

    public void set_timer(int _timer) {
        this._timer = _timer;
    }
}
