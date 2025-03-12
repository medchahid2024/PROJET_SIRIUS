package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.services.InsertCandidature;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Candidater  {

    public Button motivation;
    public Button CV;
    public Button autres;
    public Label lab;
    public Label lett;
    public Label autre;

    public TextField adresse;
    public TextField nom;
    public TextField email;
    public TextField prenom;
    public Button valider;
    FileChooser fileChooser = new FileChooser();
    int id;



    public Candidater() throws InterruptedException {
    }

    public void autres(MouseEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            autre.setText(file.getName());
        } else {
            autre.setText("Aucun fichier sélectionné");
        }

    }

    public void lettre(MouseEvent mouseEvent) {
        File file1 = fileChooser.showOpenDialog(null);
        if (file1 != null) {
            lett.setText(file1.getName());
        } else {
            lett.setText("Aucun fichier sélectionné");
        }
    }

    public void CV(MouseEvent mouseEvent) {
        File file2 = fileChooser.showOpenDialog(null);
        if (file2 != null) {
            lab.setText(file2.getName());
        } else {
            lab.setText("Aucun fichier sélectionné");
        }
    }




    public void valider(ActionEvent actionEvent) throws InterruptedException, IOException, SQLException {


        String nom1 = nom.getText();
        String prenom1 = prenom.getText();
        String email1 = email.getText();
        String adresse1 = adresse.getText();
        String lab1 = lab.getText();
        String lett1 = lett.getText();
        String autre1 = autre.getText();


        // Vérification que tous les champs sont remplis correctement
        if (nom1 == null || nom1.isEmpty() || prenom1 == null || prenom1.isEmpty() || email1 == null || email1.isEmpty()
                || adresse1 == null || adresse1.isEmpty() || lab1 == null || lab1.isEmpty() || lab1.equals("Aucun fichier sélectionné")
                || lett1 == null || lett1.isEmpty() || lett1.equals("Aucun fichier sélectionné")
                || autre1 == null || autre1.isEmpty() || autre1.equals("Aucun fichier sélectionné")) {



        if (nom1.equals("") || prenom1.equals("") || email1.equals("") || adresse1.equals("") || lab1.equals("Aucun fichier sélectionné") || lett1.equals("Aucun fichier sélectionné")|| lab1.equals("") || lett1.equals("")|| autre1.equals("Aucun fichier sélectionné")|| autre1.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {

            Candidature c = new Candidature(nom1, prenom1, email1, adresse1, lab1, lett1, autre1 , this.id );
//
            InsertCandidature.sendValue("INSERT_CANDIDATURE", c);


            Candidature cfCandidature = new Candidature(nom1, prenom1, email1, adresse1, lab1, lett1, autre1 ,id);
            InsertCandidature.sendValue("INSERT_CANDIDATURE", cfCandidature);
            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("Validé");
            aler.setHeaderText(null);
            aler.setContentText("Nous avons bien reçu votre candidature");
            aler.showAndWait();
        }

    }


}

    public void setId(int idOffre) {
        this.id = id;
        System.out.println("ID de l'offre sélectionnée : " + id);

    }
    }

