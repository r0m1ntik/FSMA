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

    public SellerUI(Seller seller) throws HeadlessException {
        // Initialise l'instance de vendeur
        this.seller = seller;

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
        JLabel _remainingTimeLabel = new JLabel(""); // étiquette pour afficher le temps restant
        // Label qui affiche le statut de l'agent de vente
        _agentStatusLabel = new JLabel("Créez votre annonce."); // étiquette pour afficher le statut du vendeur
        // étiquette pour afficher le nom de l'acheteur remportant l'enchère
        this._auctionStatusLabel = new JLabel(); // étiquette pour afficher le statut de l'enchère

        // Initialiser les champs de texte pour la création d'une annonce
        this._itemNameTextField = new JTextField("Daurade"); // champ de texte pour le nom de l'objet mis en vente
        this._initialPriceTextField = new JTextField("1000"); // champ de texte pour le prix initial de l'objet mis en vente
        this._minimumPriceIncreaseTextField = new JTextField("50"); // champ de texte pour l'incrément minimum du prix de l'objet mis en vente
        this._minimumPriceDecreaseTextField = new JTextField("50"); // champ de texte pour le décrément minimum du prix de l'objet mis en vente
        this._remainingTimeTextField = new JTextField("20"); // champ de texte pour le temps restant pour enchérir

        // Initialiser le bouton de création d'une annonce
        this._addButton = new JButton("Créer annonce");
        this._addButton.addActionListener(this);

        // Initialiser la table d'enchères
        Vector<String> nomColumn = new Vector<>(Arrays.asList("Annonce", "Prix", "Agent preneur"));
        // Vecteur contenant les données du tableau d'enchérisseurs
        Vector<Vector<String>> _biddersDataVector = new Vector<>();
        // Tableau affichant les enchérisseurs actuels et leur prix d'enchère
        JTable _biddersTable = new JTable(_biddersDataVector, nomColumn);
        _biddersTable.getTableHeader().setBackground(Color.LIGHT_GRAY); // définir la couleur
        JScrollPane propSP = new JScrollPane(_biddersTable);
        // Panel principal qui contient tous les éléments de l'interface
        JPanel _mainPanel = new JPanel();
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
        
        JPanel lineTableauPreneur = new JPanel();
        lineTableauPreneur.setLayout(new BoxLayout(lineTableauPreneur,BoxLayout.LINE_AXIS));
        lineTableauPreneur.add(propSP);
        
        JPanel lineStatut = new JPanel();
        lineStatut.setLayout(new FlowLayout(FlowLayout.LEFT));
        lineStatut.add(_agentStatusLabel);

        _mainPanel.add(lineNameAgent);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(creationLot);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(new JSeparator());
        _mainPanel.add(lineTableauPreneur);
        _mainPanel.add(new JSeparator());
        _mainPanel.add(lineStatut);
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
                    this._auctionStatusLabel.setText("Ouvert");
                    this._agentStatusLabel.setText("Annonce crée pour " + this._itemNameTextField.getText() + " au prix de " + this._initialPriceTextField.getText() + " euros.");
                    this.seller.set_nomArticle(this._itemNameTextField.getText());
                    this.seller.set_prixActuel(Integer.parseInt(this._initialPriceTextField.getText()));
                    this.seller.set_statutEnchere(this._auctionStatusLabel.getText());
                    this.seller.set_timer(tempsLot);
                    this.seller.set_incrementation(pasIncrLot);
                    this.seller.set_decrementation(pasDecrLot);
                    this.seller.set_etatEnchereTermine(true);
                    this.seller.doWake();

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
}
