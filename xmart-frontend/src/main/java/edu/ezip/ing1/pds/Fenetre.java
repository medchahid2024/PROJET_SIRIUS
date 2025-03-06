package edu.ezip.ing1.pds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Fenetre extends JFrame implements ActionListener {

    public Fenetre() {
        setTitle("Calendrier");
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

                rep = (String) JOptionPane.showInputDialog(
                        null,
                        "Quel est votre nom ?",
                        "Inscription",
                        JOptionPane.QUESTION_MESSAGE
                );

            }

            else if (source.getText().equals("<html>Picnic<br>12h - 14h</html>")) {

                rep = (String) JOptionPane.showInputDialog(
                        null,
                        "Quel est votre nom ?",
                        "Inscription",
                        JOptionPane.QUESTION_MESSAGE
                );

            }

            else if (source.getText().equals("<html>Conference<br>18h - 20h</html>")) {

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
