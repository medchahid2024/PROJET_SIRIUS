package edu.ezip.ing1.pds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import main.java.edu.ezip.ing1.pds.business.dto.Evenement;
import main.java.edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;

import main.java.edu.ezip.ing1.pds.services.EvenementService;
// import javafx.application.Platform;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Fenetre extends JFrame implements ActionListener {

    private final static String networkConfigFile = "network.yaml";
    private List<Evenement> evenementList = new ArrayList<>();
    private int currentIndex = 0;

    public void initialize() {
        try {
            loadEvenementData();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadEvenementData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final main.java.edu.ezip.ing1.pds.services.EvenementService evenementService = new EvenementService(networkConfig);

        Evenements evenements = evenementService.selectEvenements();

        if (evenements != null) {
            evenementList = new ArrayList<>(evenements.getEvenements());
            currentIndex = 0;
            afficherEvenement(currentIndex);
        }
    }

    private void afficherEvenement(int index) {

        if (evenementList.isEmpty()) {
            System.out.println("La liste des événements est vide.");
        }
        
        if (index >= evenementList.size()) {
            System.out.println("Index hors limites.");
        }


        Evenement evenement = evenementList.get(index);
        System.out.println( "Nombre :" + (String.valueOf(evenementList.size())) );
        System.out.println("Titre : " + (evenement.getTitre()) );
        System.out.println("Domaine : " + (evenement.getDomaine()) );
        System.out.println("Description : " + (evenement.getDescription()) );
        System.out.println("Heure : " + (evenement.getHeure()) );

    }

    public Fenetre() {
        setTitle("Calendrier 123");
        setSize(1280, 720);
        setLocationRelativeTo(null);

        JPanel panneau1 = new JPanel();
        panneau1.setBackground(Color.darkGray);
        panneau1.setLayout(new GridLayout(6, 7, 5, 5));

        String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

        JButton bouton1 = new JButton("<html>Tournoi Échecs<br>18h - 20h</html>");
        bouton1.addActionListener(this);
        JButton bouton2 = new JButton("<html>Picnic<br>12h - 14h</html>");
        bouton2.addActionListener(this);
        JButton bouton3 = new JButton("<html>Conference<br>18h - 20h</html>");
        bouton3.addActionListener(this);



        for (int i = 1; i <= 42; i++) {

            JPanel casePanel = new JPanel();
            casePanel.setBackground(Color.white);
            casePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            casePanel.setLayout(new BorderLayout());

            JLabel label = new JLabel(" ", SwingConstants.CENTER); // ( Texte centré sur chaque case )
            casePanel.add(label, BorderLayout.CENTER);


            if (i <= 7) {
                label.setText(jours[i - 1]);
                casePanel.setBackground(Color.LIGHT_GRAY);
            }

            if (i == 36) {
                casePanel.add(bouton1);
            }

            if (i == 25) {
                casePanel.add(bouton2);
            }

            if (i == 41) {
                casePanel.add(bouton3);
            }

            panneau1.add(casePanel);
        }

        getContentPane().add(panneau1);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            String rep;

            if (source.getText().equals("<html>Tournoi Échecs<br>18h - 20h</html>")) {

                afficherEvenement(1);
                rep = (String) JOptionPane.showInputDialog(
                        null,
                        "Quel est votre nom ?",
                        "Inscription",
                        JOptionPane.QUESTION_MESSAGE
                );

            }

            else if (source.getText().equals("<html>Picnic<br>12h - 14h</html>")) {
                afficherEvenement(2);
                rep = (String) JOptionPane.showInputDialog(
                        null,
                        "Quel est votre nom ?",
                        "Inscription",
                        JOptionPane.QUESTION_MESSAGE
                );

            }

            else if (source.getText().equals("<html>Conference<br>18h - 20h</html>")) {
                afficherEvenement(3);
                rep = (String) JOptionPane.showInputDialog(
                        null,
                        "Quel est votre nom ?",
                        "Inscription",
                        JOptionPane.QUESTION_MESSAGE
                );
            }

        }

    }
}
