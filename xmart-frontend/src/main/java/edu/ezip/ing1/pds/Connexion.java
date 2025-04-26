package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.Update;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Connexion {
    @FXML
    public PasswordField motdepasse;
    @FXML
    public Button seConnecter;
    @FXML
    public TextField email;
    @FXML
    public ToggleButton design;

    @FXML
    public ToggleButton design2;

    private List<Etudiant> stageList = new ArrayList<>();
    private int currentIndex = 0;
    private final static String networkConfigFile = "network.yaml";
    @FXML



    public void seConnecter(ActionEvent actionEvent) throws IOException, InterruptedException, SQLException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);

        String em = email.getText();
        String mdp = motdepasse.getText();
        Etudiant etudiant = new Etudiant(em, mdp);
        Etudiants etudiants = stageService.selectConnexion("SELECT_CONN", etudiant);


        if (etudiants != null && !etudiants.getEtudiants().isEmpty()) {
            stageList = new ArrayList<>(etudiants.getEtudiants());
            etudiant=stageList.get(currentIndex);
       System.out.println(etudiant.getNom());
       System.out.println(etudiant.getPrenom());


            Update.update("UPDATE_DATE",etudiant.getId());
            System.out.println(etudiant.getId());


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal2.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            Principal2 controller = loader.getController();
            controller.setEtudiant(etudiant);

            stage.show();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
       else if (email.getText().equals("admin") && motdepasse.getText().equals("admin")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        else if (email.getText().equals("") || motdepasse.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'email ou le mot de passe est incorrect");
            alert.showAndWait();
        }





    }
    public void CreateAcount(MouseEvent actionEvent) throws IOException {
        String des1 = null;
        des1 = "/CreateAcount.fxml";


        FXMLLoader loader = new FXMLLoader(getClass().getResource(des1));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void AncienDesign(ActionEvent actionEvent) throws IOException {


        String fenetre = null;
        fenetre = "/Connexion.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fenetre));
        Parent root = loader.load();
        Stage stage = (Stage) design.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void NouveauDesign(ActionEvent actionEvent) throws IOException {
        String fenetre = null;
            fenetre = "/Connexion2.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fenetre));
        Parent root = loader.load();
        Stage stage = (Stage) design2.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void CreateAcount2(MouseEvent mouseEvent) throws IOException {
        String des1 = null;
        des1 = "/CreateAccount2.fxml";


        FXMLLoader loader = new FXMLLoader(getClass().getResource(des1));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}

