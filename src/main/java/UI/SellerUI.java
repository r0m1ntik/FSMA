/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package UI;

import Model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class SellerUI extends JFrame implements ActionListener {

    // Variable représentant l'agent de vente lié à cette interface graphique
    private final Seller seller;

    private final View.Seller sellerAgent;

    // Bouton "Ajouter" pour ajouter un nouvel objet à la vente
    private final JButton _addButton;

    // Label qui affiche le statut de la vente (en cours, terminée, etc.)
    private final JLabel _auctionStatusLabel;

    // Champ de texte pour entrer le nom de l'objet à vendre
    private final JTextField _itemNameTextField;

    // Champ de texte pour entrer le prix initial de l'objet à vendre
    private final JTextField _initialPriceTextField;

    // Champ de texte pour entrer le temps restant avant la fin de la vente aux enchères
    private final JTextField _remainingTimeTextField;

    // Champ de texte pour entrer l'augmentation de prix minimum pour chaque enchère
    private final JTextField _minimumPriceIncreaseTextField;

    // Champ de texte pour entrer la diminution de prix minimum pour chaque enchère
    private final JTextField _minimumPriceDecreaseTextField;

    private final JLabel _agentStatusLabel;
    
    private final Vector<Vector<String>> _biddersDataVector;
    
    private final JPanel _mainPanel;

    private final JTable _biddersTable;

    private final JLabel _remainingTimeLabel;

    private final JLabel _lotName;
    private final JLabel _price;
    private final JLabel _lotAcheteur;
    private final JLabel _statut;

    public SellerUI(Seller seller, View.Seller sellerAgent) throws HeadlessException {
        // Initialise l'instance de vendeur
        this.seller = seller;
        this.sellerAgent = sellerAgent;

        // Initialise la fenêtre
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermer la fenêtre lorsqu'on clique sur la croix
        this.setVisible(true); // rendre la fenêtre visible
        this.setSize(800, 250); // fixer la taille de la fenêtre à 800x250 pixels
        this.setTitle(seller.get_nomAgentVendeur()); // définir le titre de la fenêtre avec le nom du vendeur

        // Récuperer la dimension de l'écran
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize(); // obtenir la taille de l'écran
        int longueur = tailleMoniteur.width / 4; // calculer la position horizontale de la fenêtre
        int hauteur = tailleMoniteur.height / 8; // calculer la position verticale de la fenêtre
        // Position au centre
        this.setLocation(longueur, hauteur); // définir la position de la fenêtre

        // Label qui affiche le temps restant avant la fin de la vente aux enchères
        _remainingTimeLabel = new JLabel(""); // étiquette pour afficher le temps restant
        // Label qui affiche le statut de l'agent de vente
        _agentStatusLabel = new JLabel("Créez votre annonce."); // étiquette pour afficher le statut du vendeur
        // étiquette pour afficher le nom de l'acheteur remportant l'enchère
        this._auctionStatusLabel = new JLabel(); // étiquette pour afficher le statut de l'enchère

        _lotName = new JLabel();
        _price = new JLabel();
        _lotAcheteur = new JLabel();
        _statut = new JLabel();

        // Initialiser les champs de texte pour la création d'une annonce
        this._itemNameTextField = new JTextField("Daurade"); // champ de texte pour le nom de l'objet mis en vente
        this._initialPriceTextField = new JTextField("1000"); // champ de texte pour le prix initial de l'objet mis en vente
        this._minimumPriceIncreaseTextField = new JTextField("50"); // champ de texte pour l'incrément minimum du prix de l'objet mis en vente
        this._minimumPriceDecreaseTextField = new JTextField("50"); // champ de texte pour le décrément minimum du prix de l'objet mis en vente
        this._remainingTimeTextField = new JTextField("20"); // champ de texte pour le temps restant pour enchérir

        // Initialiser le bouton de création d'une annonce
        this._addButton = new JButton("Créer annonce");
        this._addButton.addActionListener(this);

        JPanel gridLot = new JPanel();
        gridLot.setLayout(new GridLayout(2,4));
        gridLot.add(new JLabel("Nom lot"));
        gridLot.add(new JLabel("Prix actuel"));
        gridLot.add(new JLabel("Acheteur"));
        gridLot.add(new JLabel("Statut"));
        gridLot.add(_lotName);
        gridLot.add(_price);
        gridLot.add(_lotAcheteur);
        gridLot.add(_statut);

        // Initialiser la table d'enchères
        Vector<String> nomColumn = new Vector<>(Arrays.asList("Agent Acheteur", "Statut"));
        // Vecteur contenant les données du tableau d'enchérisseurs
        _biddersDataVector = new Vector<>();
        // Tableau affichant les enchérisseurs actuels et leur prix d'enchère
        _biddersTable = new JTable(_biddersDataVector, nomColumn);
        _biddersTable.getTableHeader().setBackground(Color.LIGHT_GRAY); // définir la couleur
        JScrollPane propSP = new JScrollPane(_biddersTable);
        // Panel principal qui contient tous les éléments de l'interface
        _mainPanel = new JPanel();
        _mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.PAGE_AXIS));
        this.setContentPane(_mainPanel);

        JPanel lineNameAgent = new JPanel();
        lineNameAgent.add(_remainingTimeLabel);
        lineNameAgent.setLayout(new BoxLayout(lineNameAgent, BoxLayout.LINE_AXIS));
        
        JPanel creationLot = new JPanel();
        creationLot.setLayout(new GridLayout(2,6));
        creationLot.add(new JLabel("Nom"));
        creationLot.add(new JLabel("Prix initial (euros)"));
        creationLot.add(new JLabel("Temps d'attente (seconde)"));
        creationLot.add(new JLabel("Incrémentation"));
        creationLot.add(new JLabel("Décrementation"));
        creationLot.add(new JLabel(""));

        creationLot.add(this._itemNameTextField);
        creationLot.add(this._initialPriceTextField);
        creationLot.add(this._remainingTimeTextField);
        creationLot.add(this._minimumPriceIncreaseTextField);
        creationLot.add(this._minimumPriceDecreaseTextField);
        creationLot.add(this._addButton);

        JPanel mineMiseEnchere = new JPanel();
        
        JPanel lineTableauPreneur = new JPanel();
        lineTableauPreneur.setLayout(new BoxLayout(lineTableauPreneur,BoxLayout.LINE_AXIS));
        lineTableauPreneur.add(propSP);

        JPanel _lineStatut = new JPanel();
        _lineStatut.setLayout(new FlowLayout(FlowLayout.LEFT));
        _lineStatut.add(_agentStatusLabel);

        _mainPanel.add(lineNameAgent);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(creationLot);
        _mainPanel.add(mineMiseEnchere);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(gridLot);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(lineTableauPreneur);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(_lineStatut);
        _mainPanel.revalidate();
        _mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // bouton crée une annonce
        if (e.getSource() == this._addButton) {
            if (this._itemNameTextField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Erreur: pas de nom de lot", "Erreur sur le nom du lot", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int prixLot;
            try {
                prixLot = Integer.parseInt(this._initialPriceTextField.getText());
                if (prixLot < 0) {
                    JOptionPane.showMessageDialog(null, "Prix est invalide, entrez un entier positif non null", "Erreur sur le prix du lot", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur sur le prix du lot", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int tempsLot;
            try {
                tempsLot = Integer.parseInt(this._remainingTimeTextField.getText());
                if (tempsLot < 2) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un temps restant valide supérieur à 2.", "Erreur sur le temps restant", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur sur le temps restant", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int pasIncrLot;
            try {
                pasIncrLot = Integer.parseInt(this._minimumPriceIncreaseTextField.getText());
                if (pasIncrLot < 1) {
                    JOptionPane.showMessageDialog(null, "Pas d'incrément invalide, veuillez entrer un nombre entier positif supérieur à 1.", "Erreur sur l'incrémentation", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur sur l'incrémentation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int pasDecrLot;
            try {
                pasDecrLot = Integer.parseInt(this._minimumPriceDecreaseTextField.getText());
                if (pasDecrLot < 1) {
                    JOptionPane.showMessageDialog(null, "Pas de décrément invalide, veuillez entrer un nombre entier positif supérieur à 1.", "Erreur sur la décrémentation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //
                    this._agentStatusLabel.setText("Annonce crée pour " + this._itemNameTextField.getText() + " au prix de " + this._initialPriceTextField.getText() + " euros.");

                    // vente
                    this._lotName.setText(this._itemNameTextField.getText());
                    this._price.setText(this._initialPriceTextField.getText());
                    this._statut.setText("Ouvert");

                    // offres
                    this.seller.set_nomArticle(this._itemNameTextField.getText());
                    this.seller.set_prixActuel(Integer.parseInt(this._initialPriceTextField.getText()));
                    this.seller.set_statutEnchere(this._statut.getText());
                    this.seller.set_timer(tempsLot);
                    this.seller.set_incrementation(pasIncrLot);
                    this.seller.set_decrementation(pasDecrLot);
                    this.seller.set_etatInitialisationTermine(true);
                    this.sellerAgent.doWake();

                    this._itemNameTextField.setEnabled(false);
                    this._initialPriceTextField.setEnabled(false);
                    this._minimumPriceIncreaseTextField.setEnabled(false);
                    this._minimumPriceDecreaseTextField.setEnabled(false);
                    this._remainingTimeTextField.setEnabled(false);
                    this._addButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur sur la décrémentation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void updateTableAcheteur(int positionAnnonce, Vector<String> originDataVector) {
        this._biddersDataVector.set(positionAnnonce, originDataVector);
        refreshGUI();
    }

    public void addTableAcheteur(Vector<String> originDataVector) {
        this._biddersDataVector.add(originDataVector);
        refreshGUI();
    }

    public void resetStatutAcheteur() {
        for (int i = 0; i < this._biddersDataVector.size(); i++){
            Vector<String> data = this._biddersDataVector.get(i);
            data.set(1,"N'as pas encore propose");
            this._biddersDataVector.set(i, data);
        }
        refreshGUI();
    }
    private void refreshGUI() {
        this._biddersTable.invalidate();
        this._biddersTable.revalidate();
        this._biddersTable.repaint();
        this._mainPanel.invalidate();
        this._mainPanel.revalidate();
        this._mainPanel.repaint();
    }

    public void finEnchere(String gagnant) {
        this._remainingTimeLabel.setText("Enchére terminé!");
        this._lotAcheteur.setText(gagnant);
    }

    public void setPrixActuelAffichage(int prix){
        this._initialPriceTextField.setText(String.valueOf(prix));
    }
    
    public void setStatutLot(String text){
        this._auctionStatusLabel.setText(text);
    }
    public void setStatut(String text){
        this._agentStatusLabel.setText(text);
    }
    public void setTempsRestant(String text){
        this._remainingTimeLabel.setText(" Temps: " + text + " secondes");
    }
}
