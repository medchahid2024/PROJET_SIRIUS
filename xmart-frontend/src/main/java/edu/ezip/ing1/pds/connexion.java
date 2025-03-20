package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class connexion {
    public PasswordField motdepasse;
    public Button seConnecter;
    public TextField email;
    private List<Etudiant> stageList = new ArrayList<>();
    private int currentIndex = 0;
    private final static String networkConfigFile = "network.yaml";


    public void seConnecter(ActionEvent actionEvent) throws IOException, InterruptedException {
//        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
//        final stageService stageService = new stageService(networkConfig);
//
//        Etudiants etudiants = stageService.selectConnexion();
//        if (etudiants != null && !etudiants.getEtudiants().isEmpty()) {
//            stageList = new ArrayList<>(etudiants.getEtudiants());
//        int index=0;
//        Etudiant etudiant = stageList.get(index);
//        String em=etudiant.getEmail();
//        String mdp= etudiant.getMot_de_passe();
//        email.setText(em);
//        motdepasse.setText(mdp);}
//



        if (email.getText().equals("admin")&&motdepasse.getText().equals("admin")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
       else if (email.getText().equals("etudiant")&&motdepasse.getText().equals("etudiant")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        else if (email.getText().equals("")||motdepasse.getText().equals("")) {
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

    public void CreateAcount(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAcount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
