package edu.ezip.ing1.pds;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal2 {

    public Label fullName;
    @FXML
    private Button BClose;

    @FXML
    private Button BOpen;

    @FXML
    private Button Deconnexion;

    @FXML
    private Pane Panneau;

    @FXML
    private ImageView Image;

    @FXML
    private Button BEven;

    @FXML
    private Button BServices;

    @FXML
    private Button BStages;

    private Etudiant etudiant;




    @FXML
    void BEven(ActionEvent event) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InterruptedException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Calendrier2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        // Récupérer le contrôleur de la vue Calendrier
        Calendrier2Controller calendrier2Controller = loader.getController();

        // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
        calendrier2Controller.InitialisationCalendrier();
        calendrier2Controller.setEtudiant(this.etudiant);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void BStages(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        ControlStage controller = loader.getController();
        controller.setEtudiant(this.etudiant);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void BServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connexion2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private TranslateTransition translate = new TranslateTransition();



    @FXML
    void Deconnecter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connexion2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void BClose(MouseEvent event) {
        BClose.setLayoutX(-500);
        BOpen.setLayoutX(0);

        translate.setNode(Panneau);
        translate.setByX(-207);
        translate.play();


    }

    @FXML
    void BOpen(MouseEvent event) {
        BOpen.setLayoutX(-200);
        BClose.setLayoutX(207);

        translate.setNode(Panneau);
        translate.setByX(207);
        translate.play();


    }


    public void setEtudiant(Etudiant etudiant) {
        if (etudiant != null) {
            this.etudiant = etudiant;

            fullName.setText(etudiant.getNom() + " " + etudiant.getPrenom());
            System.out.println(etudiant.getNom() );
            System.out.println(etudiant.getPrenom());

        }
}

    public void profil(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profil.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
//        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//        currentStage.close();
    }
}