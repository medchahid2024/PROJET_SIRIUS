package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CreateAcount {
    @FXML
    public TextField cnf_mot_de_passe;
    @FXML
    public TextField mot_de_passe;
    @FXML
    public TextField Nom;
    @FXML
    public TextField prenom;
    @FXML
    public TextField email;
    @FXML
    public Button seConnecter;
    @FXML
    public TextField Matricule;
    @FXML
    public Label photo;
    @FXML
    public Button fichier;
    FileChooser fileChooser = new FileChooser();
    int id;
    private final static String networkConfigFile = "network.yaml";


    public void connexion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Connexion.fxml"));
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


        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);
        Etudiant etudiant1 = new Etudiant(Email);
        Etudiants etudiants = stageService.selectConnexion("SELECT_CONDITION_CONN", etudiant1);



        if (matricule == null || matricule.isEmpty() ||
                Prenom == null || Prenom.isEmpty() || nom == null || nom.isEmpty() ||
                Email == null || Email.isEmpty() || MotDePasse == null || MotDePasse.isEmpty() ||
                Conf_mdp == null || Conf_mdp.isEmpty() || Photo == null || Photo.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement.");
            alert.showAndWait();

        } else if (!MotDePasse.equals(Conf_mdp)) {

            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText(null);
            alert1.setContentText("Les mots de passe ne sont pas similaires.");
            alert1.showAndWait();

        } else if (!Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { // Vérification améliorée

            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erreur");
            alert2.setHeaderText(null);
            alert2.setContentText("Le format de l'adresse email est incorrect.");
            alert2.showAndWait();

        } else if (!(Photo.endsWith(".png") || Photo.endsWith(".jpg") || Photo.endsWith(".jpeg"))) {

            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur");
            alert3.setHeaderText(null);
            alert3.setContentText("Le fichier image doit être un .png ou .jpg.");
            alert3.showAndWait();

        } else if (!MotDePasse.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) { // 8 caractères, 1 majuscule, 1 chiffre

            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("Erreur");
            alert4.setHeaderText(null);
            alert4.setContentText("Le mot de passe doit contenir au moins 8 caractères, une majuscule et un chiffre.");
            alert4.showAndWait();

        } else if (!nom.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ-]+$")) { // Vérifie que le nom ne contient que des lettres

            Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Erreur");
            alert5.setHeaderText(null);
            alert5.setContentText("Le nom ne doit contenir que des lettres.");
            alert5.showAndWait();

        } else if (!Prenom.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ-]+$")) { // Vérifie que le prénom ne contient que des lettres

            Alert alert6 = new Alert(Alert.AlertType.ERROR);
            alert6.setTitle("Erreur");
            alert6.setHeaderText(null);
            alert6.setContentText("Le prénom ne doit contenir que des lettres.");
            alert6.showAndWait();

        }
       else  if (etudiants != null && !etudiants.getEtudiants().isEmpty()) {
            Alert alert7 = new Alert(Alert.AlertType.ERROR);
            alert7.setTitle("Erreur");
            alert7.setHeaderText(null);
            alert7.setContentText("vous avez déja  un compte \n veuillez contacté la scolarité en cas d'oublie de vos identifiants");
            alert7.showAndWait();

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
            aler.setContentText("Nous avons bien reçu votre demande , il sera traiter dans 24h");
            Optional<ButtonType> result = aler.showAndWait();
            if (result.get() == ButtonType.OK) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Connexion.fxml"));
                Stage fenetre = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                fenetre.setScene(scene);
                fenetre.show();

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                System.out.println("Demande transmis");

            }
            else{
                System.out.println("Demande refusée");
            }

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

    public void connexion2(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Connexion2.fxml"));
        Stage stage = new Stage();
        Scene scene= new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
