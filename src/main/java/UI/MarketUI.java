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
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class MarketUI extends JFrame {

    private Vector<Vector<String>> data;
    private JTable buyersTable;
    private JPanel mainPanel;

    public MarketUI(Model.Market market) {

        // Initialisation de la fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 250);
        this.setTitle(market.get_MarketName());

        //récuperer la dimension de l'écran
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width / 4;
        int hauteur = tailleMoniteur.height * 4 / 10;
        // Position au centre
        this.setLocation(longueur, hauteur);

        // Liste des composants de la fenêtre
        Vector<String> columnNames = new Vector<>(Arrays.asList("Vendeur", "Nom du lot", "Montant courant de l'enchère"));
        data = new Vector<>();
        buyersTable = new JTable(data, columnNames);
        buyersTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        JScrollPane buyersScrollPane = new JScrollPane(buyersTable);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setContentPane(mainPanel);
        JPanel agentStatusPanel = new JPanel();
        JPanel windowTitlePanel = new JPanel();
        JPanel buyersPanel = new JPanel();

        // Configuration des composants de la fenêtre
        agentStatusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buyersPanel.setLayout(new BoxLayout(buyersPanel, BoxLayout.LINE_AXIS));
        buyersPanel.add(buyersScrollPane);
        windowTitlePanel.setLayout(new BoxLayout(windowTitlePanel, BoxLayout.LINE_AXIS));
        mainPanel.add(windowTitlePanel);
        mainPanel.add(new JSeparator());
        mainPanel.add(buyersPanel);
        mainPanel.add(new JSeparator());
        mainPanel.add(agentStatusPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void majTable(int positionAnnonce,Vector<String> originDataVector) {
        this.data.set(positionAnnonce, originDataVector);
        updateInter();
    }

    public void ajoutNewTable(Vector<String> originDataVector) {
        this.data.add(originDataVector);
        updateInter();
    }

    private void updateInter() {
        this.buyersTable.invalidate();
        this.buyersTable.revalidate();
        this.buyersTable.repaint();
        this.mainPanel.invalidate();
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }
}
