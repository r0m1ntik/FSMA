/*
 *  Copyright (c) 2023,
 *  * Roman BADANIN
 *  * Loic LALANNE
 *  * All Rights Reserved.
 *  * Created for project FSMA (M2 TI - Université de PAU)
 *
 */

package UI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class BuyerUI extends JFrame implements ActionListener {

    // Déclaration des variables avec des noms explicites
    private final Model.Buyer buyer; // Agent preneur
    private final JButton boutonManuel; // Bouton pour mode manuel
    private final JButton boutonAutomatique; // Bouton pour mode automatique
    private final JButton boutonDemarrerEnchere; // Bouton pour démarrer l'enchère
    private final JButton boutonProposer; // Bouton pour proposer un prix
    private final JLabel statutLabel; // Étiquette pour le statut de l'enchère
    private final JTextField budgetTexte; // Champ de texte pour le budget
    private final JTable tableauEnchere; // Tableau pour afficher les enchères

    // Constructeur
    public BuyerUI(Model.Buyer preneurAgent) {
        // Initialisation des variables
        this.buyer = preneurAgent;
        this.boutonManuel = new JButton("Démarrer en manuel");
        this.boutonAutomatique = new JButton("Démarrer en automatique");
        this.boutonDemarrerEnchere = new JButton("Passer aux enchères!");
        this.boutonProposer = new JButton("Proposer");

        // Panneaux
        // Panneau principal
        JPanel panneauPrincipal = new JPanel();
        this.statutLabel = new JLabel("Choisissez votre mode de fonctionnement (manuel ou automatique).");
        this.budgetTexte = new JTextField(10);
        this.budgetTexte.setText("1500");

        // initialise la fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 250);
        this.setTitle(buyer.get_buyerName());

        //récuperer la dimension de l'écran
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width / 4;
        int hauteur = tailleMoniteur.height * 4 / 6;
        // Position au centre
        this.setLocation(longueur, hauteur);

        // Tableau
        // Données du tableau
        Vector<Vector<String>> donneesTableau = new Vector<>();
        CellObjectRenderer objRender = new CellObjectRenderer();
        Vector<String> columnNames = new Vector<>(Arrays.asList("Vendeur", "Nom du lot", "Prix"));
        this.tableauEnchere = new JTable(donneesTableau, columnNames);
        this.tableauEnchere.getTableHeader().setBackground(Color.LIGHT_GRAY);

        this.tableauEnchere.setDefaultRenderer(java.lang.Object.class,objRender);
        this.tableauEnchere.getColumnModel().getColumn(0).setMinWidth(150);
        this.tableauEnchere.getColumnModel().getColumn(1).setMinWidth(150);
        this.tableauEnchere.getColumnModel().getColumn(2).setMinWidth(150);

        // Ajout des composants au panneau principal
        panneauPrincipal.add(this.boutonManuel);
        panneauPrincipal.add(this.boutonAutomatique);
        panneauPrincipal.add(this.boutonDemarrerEnchere);
        panneauPrincipal.add(this.budgetTexte);
        panneauPrincipal.add(this.boutonProposer);

        JScrollPane enchereScrollPane = new JScrollPane(this.tableauEnchere);
        panneauPrincipal.add(enchereScrollPane);

        panneauPrincipal.add(this.statutLabel);

        // Ajout des actions aux boutons
        this.boutonManuel.addActionListener(this);
        this.boutonAutomatique.addActionListener(this);
        this.boutonDemarrerEnchere.addActionListener(this);
        this.boutonProposer.addActionListener(this);

        panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal, BoxLayout.PAGE_AXIS));
        this.setContentPane(panneauPrincipal);

        // Création des panneaux pour chaque ligne
        JPanel panelNameAgent = new JPanel();      // Nom de l'agent vendeur
        JPanel panelChoice = new JPanel();         // Choix de démarrer en manuel ou automatique
        JPanel panelMode = new JPanel();           // Mode de fonctionnement (manuel/automatique)
        JPanel panelEnchereMode = new JPanel();    // Mode d'enchère
        JPanel panelTableEnchere = new JPanel();   // Tableau d'enchères
        JPanel panelProp = new JPanel();           // Proposition d'enchère
        JPanel panelStatut = new JPanel();         // Statut de l'enchère
        JPanel panelActReq = new JPanel();         // Actions et demandes

        // Définition des layouts pour chaque panneau
        // Met en place un layout horizontal pour la ligne contenant le nom de l'agent vendeur
        panelNameAgent.setLayout(new BoxLayout(panelNameAgent, BoxLayout.LINE_AXIS));
        // Met en place un layout de grille 1x4 pour la ligne de choix d'options
        panelChoice.setLayout(new GridLayout(1, 4));
        // Ajoute un bouton de démarrage manuel à la ligne de choix
        panelChoice.add(this.boutonManuel);
        // Ajoute un label pour le budget et un champ de texte pour entrer le budget à la ligne de choix
        panelChoice.add(new JLabel(" Somme limite (euros): "));
        panelChoice.add(this.budgetTexte);
        // Ajoute un bouton de démarrage automatique à la ligne de choix
        panelChoice.add(this.boutonAutomatique);
        // Met en place un layout horizontal pour la ligne contenant le tableau d'enchères
        panelTableEnchere.setLayout(new BoxLayout(panelTableEnchere, BoxLayout.LINE_AXIS));
        // Ajoute un JScrollPane contenant le tableau d'enchères à la ligne de tableau d'enchères
        panelTableEnchere.add(enchereScrollPane);
        // Met en place un layout de flux à gauche pour la ligne de proposition
        panelProp.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Ajoute un bouton pour démarrer les enchères et le désactive
        panelProp.add(this.boutonDemarrerEnchere);
        this.boutonDemarrerEnchere.setEnabled(false);
        // Ajoute un bouton pour proposer un prix et le désactive
        panelProp.add(this.boutonProposer);
        this.boutonProposer.setEnabled(false);
        // Met en place un layout de flux à gauche pour la ligne de statut
        panelStatut.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Ajoute un label de statut à la ligne de statut
        panelStatut.add(this.statutLabel);
        // Met en place un layout de flux à gauche pour la ligne d'actions requises
        panelActReq.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Ajoute le panneau de ligne contenant le nom de l'agent vendeur au panneau principal
        panneauPrincipal.add(panelNameAgent);
        // Ajoute une séparation à la ligne suivante dans le panneau principal
        panneauPrincipal.add(new JSeparator());
        // Ajoute le panneau de ligne de choix d'options au panneau principal
        panneauPrincipal.add(panelChoice);
        // Ajoute la ligne de mode au panneau principal (non fournie dans le code)
        panneauPrincipal.add(panelMode);
        // Ajoute la ligne de mode d'enchère au panneau principal (non fournie dans le code)
        panneauPrincipal.add(panelEnchereMode);
        // Ajoute le panneau de ligne de tableau d'enchères au panneau principal
        panneauPrincipal.add(panelTableEnchere);
        // Ajoute le panneau de ligne de proposition au panneau principal
        panneauPrincipal.add(panelProp);
        // Ajoute une séparation à la ligne suivante dans le panneau principal
        panneauPrincipal.add(new JSeparator());
        // Ajoute la ligne de statut au panneau principal
        panneauPrincipal.add(panelStatut);
        // Ajoute la ligne d'actions requises au panneau principal
        panneauPrincipal.add(panelActReq);
        // Rafraîchit l'affichage du panneau principal pour prendre en compte les modifications
        panneauPrincipal.revalidate();
        panneauPrincipal.repaint();
    }

    public void setStatut(String text){
        this.statutLabel.setText(text);
    }

    public int[] getSelectedRows(){
        return this.tableauEnchere.getSelectedRows();
    }

    public void setEnableBoutonPropose(boolean isActive){
        this.boutonProposer.setEnabled(isActive);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRowsTableEnchere = this.getSelectedRows();

        Object source = e.getSource();
        if (source.equals(this.boutonManuel)) {
            this.boutonManuel.setEnabled(false);
            this.boutonAutomatique.setEnabled(false);
            this.budgetTexte.setEnabled(false);
            this.boutonDemarrerEnchere.setEnabled(true);
            this.setStatut("Prêt à rejoindre une enchère.");
            this.buyer.set_buyerType("Manuel");
            this.buyer.set_buyerMode(true);
        } else if (source.equals(this.boutonAutomatique)) {
            try {
                this.buyer.set_buyerBudget(Integer.parseInt(this.budgetTexte.getText()));
            } catch (NumberFormatException nfe) {
                this.buyer.set_buyerBudget(-1);
            }
            if (this.buyer.get_buyerBudget() > 0) {
                this.buyer.set_buyerBudget(this.buyer.get_buyerBudget());
                this.boutonManuel.setEnabled(false);
                this.boutonAutomatique.setEnabled(false);
                this.boutonDemarrerEnchere.setEnabled(true);
                this.budgetTexte.setEnabled(false);
                this.setStatut("Prêt à rejoindre une enchère.");
                this.buyer.set_buyerType("Automatique");
                this.buyer.set_buyerMode(true);
                this.buyer.doWake();
            } else {
                JOptionPane.showMessageDialog(null, "S'il vous plaît entrer un nombre positif non nul.",
                        "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (source.equals(this.boutonDemarrerEnchere)) {
            this.buyer.set_buyerAnnounces(selectedRowsTableEnchere);
            this.buyer.set_buyerAnnonceSelecte(true);
            this.setEnableBoutonPropose(false);
            this.boutonDemarrerEnchere.setEnabled(false);
            this.buyer.doWake();
        } else if (source.equals(this.boutonProposer)) {
            this.setStatut("offre(s) enchérie(s).");
            this.buyer.set_buyerProposition(true);
            this.buyer.doWake();
        }
    }

    protected static class CellObjectRenderer extends JLabel implements TableCellRenderer {
        public CellObjectRenderer()
        {
            super();
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Object cellule = table.getValueAt(row,3);
            String ValCell = (String)cellule;
            setText((String) value);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            if (isSelected) {
                setBackground(Color.BLUE);
                setForeground(Color.WHITE);
            }

            if (ValCell.equals("Enchère gagnée !")) {
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
            }

            if (ValCell.equals("Enchère terminee")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);
            }

            return this;
        }
    }
}
