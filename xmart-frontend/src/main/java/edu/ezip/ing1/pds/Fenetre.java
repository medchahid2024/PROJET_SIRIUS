package edu.ezip.ing1.pds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Evenement;
import edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.business.dto.Participations;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;

import edu.ezip.ing1.pds.services.EvenementService;
// import javafx.application.Platform;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.InsertParticipation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class Fenetre extends JFrame implements ActionListener{

    private final static String networkConfigFile = "network.yaml";
    private List<Evenement> evenementList = new ArrayList<>();
    private int currentIndex = 0;





    private void loadEvenementData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final EvenementService evenementService = new EvenementService(networkConfig);

        Evenements evenements = evenementService.selectEvenements();

        if (evenements != null) {
            evenementList = new ArrayList<>(evenements.getEvenements());
            currentIndex = 0;
            
        }
    }

    private void participer() throws SQLException, IOException, InterruptedException {
        Participation parti = new Participation("Arsalane", "prenom1", "email1", 1);
        InsertParticipation.sendValue("INSERT_PARTICIPATION", parti);
           
    }
    private void afficherEvenement(int index) {

        if (evenementList.isEmpty()) {
            System.out.println("La liste des événements est vide.");
            return;
        }
        
        if (index >= evenementList.size()) {
            System.out.println("Index hors limites.");
            return;
        }


        Evenement evenement = evenementList.get(index);
        System.out.println( "Nombre d'évenements:" + (String.valueOf(evenementList.size())) );
        System.out.println("Titre : " + (evenement.getTitre()) );
        System.out.println("Domaine : " + (evenement.getDomaine()) );
        System.out.println("Description : " + (evenement.getDescription()) );
        System.out.println("Heure : " + (evenement.getHeure()) );
        System.out.println("Id = " + (evenement.getId()));

    }


    public Fenetre() throws IOException, InterruptedException {
        loadEvenementData();

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

                afficherEvenement(0);
                afficherEvenement(1);
                afficherEvenement(2);
                afficherEvenement(3);
                afficherEvenement(4);

            
            
                try {
                    participer();
                } catch (IOException | SQLException | InterruptedException e2) {
                    e2.printStackTrace(); // Affiche l'erreur dans la console
                    JOptionPane.showMessageDialog(null, "Erreur lors de la participation : " + e2.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
                
            


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
