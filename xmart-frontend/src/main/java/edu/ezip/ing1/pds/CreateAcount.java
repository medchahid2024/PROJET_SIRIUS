package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.services.InsertCandidature;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAcount {
    public TextField cnf_mot_de_passe;
    public TextField mot_de_passe;
    public TextField Nom;
    public TextField prenom;
    public TextField email;
    public Button seConnecter;
    public TextField Matricule;
    public Label photo;
    public Button fichier;
    FileChooser fileChooser = new FileChooser();
    int id;


    public void connexion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/connexion.fxml"));
        Stage stage = new Stage();
        Scene scene= new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }


    public void seConnecter(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {
        String Photo= photo.getText();
        String matricule = Matricule.getText();
        String nom = Nom.getText();
        String Prenom= prenom.getText();
        String Email = email.getText();
        String MotDePasse = mot_de_passe.getText();
        String Conf_mdp = cnf_mot_de_passe.getText();


        if (matricule == null || matricule.isEmpty() || Prenom == null || Prenom.isEmpty() || nom == null || nom.isEmpty()
                || Email == null || Email.isEmpty() || MotDePasse == null || MotDePasse.isEmpty() ||Conf_mdp == null || Conf_mdp.isEmpty() ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement.");
            alert.showAndWait();}
          else   if (!MotDePasse.equals(Conf_mdp)){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Erreur");
                alert1.setHeaderText(null);
                alert1.setContentText("Les mots de passes ne sont pas similaires.");
                alert1.showAndWait();

            }
           else if (!Email.contains("@")){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erreur");
            alert2.setHeaderText(null);
            alert2.setContentText("le format de l'adresse de mail est incorrect.");
            alert2.showAndWait();

        }
           else if(!(Photo.endsWith(".png") || Photo.endsWith(".jpg")) ){
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur");
            alert3.setHeaderText(null);
            alert3.setContentText("Le fichier image doit être un .png ou .jpg.");
            alert3.showAndWait();

        }
         else {

            Etudiant etudiant = new Etudiant(nom,Prenom,matricule,Email,MotDePasse,Conf_mdp,Photo);

            System.out.println( nom);
            System.out.println( Prenom);
            System.out.println( Email);
            System.out.println( MotDePasse);
            System.out.println( Conf_mdp);
            System.out.println( Photo);


            InsertCandidature.sendValeur("INSERT_ETUDIANT", etudiant);

            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("Validé");
            aler.setHeaderText(null);
            aler.setContentText("Nous avons bien reçu votre demande");
            aler.showAndWait();
        }



    }

    public void fichier(MouseEvent mouseEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            photo.setText(file.getName());
        } else {
            photo.setText("Aucun fichier sélectionné");
        }

    }
}
