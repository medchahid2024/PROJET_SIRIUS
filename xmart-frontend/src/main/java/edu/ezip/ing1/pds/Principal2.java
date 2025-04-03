package edu.ezip.ing1.pds;


import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal2 {

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

    @FXML
    void BEven(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/Calendrier.fxml"));
      Stage stage = new Stage();
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
      stage.show();
      Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      currentStage.close();
    }

    @FXML
    void BStages(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/control_stage.fxml"));
      Stage stage = new Stage();
      Scene scene = new Scene(loader.load());
      stage.setScene(scene);
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

}
