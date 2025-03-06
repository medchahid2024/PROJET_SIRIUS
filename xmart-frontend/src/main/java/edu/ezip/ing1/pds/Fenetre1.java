package edu.ezip.ing1.pds;

import java.awt.*;
import javax.swing.*;

public class Fenetre1 extends JFrame {


    private JPanel contentPanel;


    private JPanel panneauPrincipal;
    private JPanel panneauAnnonces;
    private JPanel panneauCommerces;
    private JPanel panneauTransactions;
    private JPanel panneauAcheter;
    private JPanel panneauVendre;
    private JPanel panneauEchanger;
    private JPanel commerceDetailsPanel;

    public Fenetre1() {
        super("Fenêtre1");

        setSize(700, 672);
        setLocationRelativeTo(null);


        panneauPrincipal = creerPanneauPrincipal();
        panneauAnnonces = creerPanneauAnnonces();
        panneauCommerces = creerPanneauCommerces();
        panneauTransactions = creerPanneauTransactions();
        panneauAcheter = creerPanneauArticle("acheter");
        panneauVendre = creerPanneauArticle("Vendre");
        panneauEchanger = creerPanneauArticle("Échanger");

        commerceDetailsPanel = new JPanel(new BorderLayout());


        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel);


        setContentPanel(panneauPrincipal);
    }


    private void setContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private JPanel creerPanneauPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        Dimension ButtonSize = new Dimension(600, 120);


        panel.add(Box.createVerticalStrut(50));


        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonAnnonces = new JButton("Annonces");
        boutonAnnonces.setPreferredSize(ButtonSize);
        boutonAnnonces.addActionListener(e -> setContentPanel(panneauAnnonces));
        Panel.add(boutonAnnonces);
        panel.add(Panel);


        panel.add(Box.createVerticalStrut(50));


        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonTransactions = new JButton("Acheter, vendre, échanger");
        boutonTransactions.setPreferredSize(ButtonSize);
        boutonTransactions.addActionListener(e -> setContentPanel(panneauTransactions));
        middlePanel.add(boutonTransactions);
        panel.add(middlePanel);


        panel.add(Box.createVerticalStrut(50));


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonCommerces = new JButton("Commerces et services");
        boutonCommerces.setPreferredSize(ButtonSize);
        boutonCommerces.addActionListener(e -> setContentPanel(panneauCommerces));
        bottomPanel.add(boutonCommerces);
        panel.add(bottomPanel);


        panel.add(Box.createVerticalStrut(50));

        return panel;
    }


    private JPanel creerPanneauAnnonces() {
        JPanel panneau = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Pas d'annonces pour le moment", SwingConstants.CENTER);
        panneau.add(label, BorderLayout.CENTER);

        JPanel zoneBas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonRetour = new JButton("Annuler");
        boutonRetour.addActionListener(e -> setContentPanel(panneauPrincipal));
        zoneBas.add(boutonRetour);

        panneau.add(zoneBas, BorderLayout.SOUTH);
        return panneau;
    }

    private JPanel creerPanneauCommerces() {
        JPanel panel = new JPanel(new BorderLayout());


        String[][] donneesCommerces = {
                {"Librairie upec", "45 Avenue des Livres", "09:00-18:00", "15% sur livres", "0987654321"},
                {"Café Campus", "123 Rue Universitaire", "08:00-17:30", "10% réduction étudiants", "0123456789"},
                {"Restaurant Universitaire", "6 Place des Étudiants", "09:00-17:30", "Menu étudiant", "0123987654"},
                {"SUPERMARCHÉ G20", "78 Boulevard des Saveurs", "07:00-22:00", "Offres du jour", "0123567890"},
                {"Pharmacie du Campus", "90 Rue de la Santé", "08:00-22:00", "Promotions sur produits", "0123678901"}
        };


        JPanel listPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        Dimension dimBoutonCommerce = new Dimension(600, 40);

        for (String[] commerce : donneesCommerces) {
            String nom = commerce[0];
            String adresse = commerce[1];
            String horaires = commerce[2];
            String promotions = commerce[3];
            String contact = commerce[4];

            JButton boutonCommerce = new JButton(nom);
            boutonCommerce.setPreferredSize(dimBoutonCommerce);


            boutonCommerce.addActionListener(e -> {
                afficherDetailsCommerce(nom, adresse, horaires, promotions, contact);
                setContentPanel(commerceDetailsPanel);
            });

            listPanel.add(boutonCommerce);
        }

        panel.add(listPanel, BorderLayout.CENTER);


        JPanel cancel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(e -> setContentPanel(panneauPrincipal));
        cancel.add(boutonAnnuler);

        panel.add(cancel, BorderLayout.SOUTH);
        return panel;
    }

    private void afficherDetailsCommerce(String nom, String adresse, String horaires, String promotions, String contact) {
        commerceDetailsPanel.removeAll();


        JLabel titre = new JLabel(nom, SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        commerceDetailsPanel.add(titre, BorderLayout.NORTH);

        JPanel panneauDetails = new JPanel(new BorderLayout());
        String texteDetails = "<html>"
                + "<b>Adresse :</b> " + adresse + "<br/>"
                + "<b>Horaires :</b> " + horaires + "<br/>"
                + "<b>Promotions :</b> " + promotions + "<br/>"
                + "<b>Contact :</b> " + contact
                + "</html>";
        JLabel labelDetails = new JLabel(texteDetails);
        panneauDetails.add(labelDetails, BorderLayout.NORTH);
        commerceDetailsPanel.add(panneauDetails, BorderLayout.CENTER);

        // Boutons en bas (SOUTH)
        JPanel cancel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonRetour = new JButton("Annuler");
        boutonRetour.addActionListener(e -> setContentPanel(panneauCommerces));

        JButton boutonVoirAvis = new JButton("Voir avis");
        boutonVoirAvis.addActionListener(e -> afficherAvis(nom));

        JButton boutonAjouterAvis = new JButton("Ajouter avis");
        boutonAjouterAvis.addActionListener(e -> ajouterAvis(nom));

        cancel.add(boutonRetour);
        cancel.add(boutonVoirAvis);
        cancel.add(boutonAjouterAvis);

        commerceDetailsPanel.add(cancel, BorderLayout.SOUTH);

        commerceDetailsPanel.revalidate();
        commerceDetailsPanel.repaint();
    }

    private void afficherAvis(String nomCommerce) {
        String[] avisFictifs = {
                "User1 : Excellente qualité de service.",
                "User2 : Bon rapport qualité/prix.",
                "User3 : L'ambiance est agréable.",
                "User4 : Personnel sympathique.",
                "User5 : À recommander pour les étudiants."
        };
        JList<String> listeAvis = new JList<>(avisFictifs);
        listeAvis.setVisibleRowCount(5);

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(listeAvis),
                "Avis pour " + nomCommerce,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void ajouterAvis(String nomCommerce) {
        JPanel panneau = new JPanel(new BorderLayout(5, 5));
        panneau.add(new JLabel("Entrez votre avis pour " + nomCommerce + " :"), BorderLayout.NORTH);
        JTextArea zoneTexteAvis = new JTextArea(5, 20);
        panneau.add(new JScrollPane(zoneTexteAvis), BorderLayout.CENTER);

        int resultat = JOptionPane.showConfirmDialog(
                this,
                panneau,
                "Ajouter avis",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultat == JOptionPane.OK_OPTION) {
            String avis = zoneTexteAvis.getText().trim();
            if (!avis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Avis ajouté : " + avis);
            }
        }
    }

    private JPanel creerPanneauTransactions() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        Dimension bigButtonSize = new Dimension(600, 80);


        panel.add(Box.createVerticalStrut(40));
        JPanel Acheter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonAcheter = new JButton("Acheter");
        boutonAcheter.setPreferredSize(bigButtonSize);
        boutonAcheter.addActionListener(e -> setContentPanel(panneauAcheter));
        Acheter.add(boutonAcheter);
        panel.add(Acheter);

        panel.add(Box.createVerticalStrut(40));

        JPanel Vendre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonVendre = new JButton("Vendre");
        boutonVendre.setPreferredSize(bigButtonSize);
        boutonVendre.addActionListener(e -> setContentPanel(panneauVendre));
        Vendre.add(boutonVendre);
        panel.add(Vendre);

        panel.add(Box.createVerticalStrut(40));


        JPanel Echanger = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonEchanger = new JButton("Échanger");
        boutonEchanger.setPreferredSize(bigButtonSize);
        boutonEchanger.addActionListener(e -> setContentPanel(panneauEchanger));
        Echanger.add(boutonEchanger);
        panel.add(Echanger);

        panel.add(Box.createVerticalStrut(40));


        JPanel cancel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(e -> setContentPanel(panneauPrincipal));
        cancel.add(boutonAnnuler);
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        container.add(cancel, BorderLayout.SOUTH);

        return container;
    }

    private JPanel creerPanneauArticle(String typeTransaction) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel message = new JLabel("Pas d'articles pour le moment", SwingConstants.CENTER);
        panel.add(message, BorderLayout.CENTER);

        JPanel cancel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonAnnuler = new JButton("Annuler");

        boutonAnnuler.addActionListener(e -> setContentPanel(panneauTransactions));
        cancel.add(boutonAnnuler);

        panel.add(cancel, BorderLayout.SOUTH);

        return panel;
    }



}
