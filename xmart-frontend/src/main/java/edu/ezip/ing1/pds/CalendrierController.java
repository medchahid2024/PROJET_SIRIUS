package edu.ezip.ing1.pds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class CalendrierController {

    @FXML
    private Button Bouton1;

    @FXML
    private Button Bouton2;

    @FXML
    private Button Bouton3;

    @FXML
    private Button Bouton4;

    @FXML
    private Button Bouton5;

    @FXML
    private Button Bouton6;

    @FXML
    private Button Bouton7;

    @FXML
    private Label Heure1;

    @FXML
    private Label Heure2;

    @FXML
    private Label Heure3;

    @FXML
    private Label Heure4;

    @FXML
    private Label Heure5;

    @FXML
    private Label Heure6;

    @FXML
    private Label Heure7;

    @FXML
    private Label Label1;

    @FXML
    private Label Label2;

    @FXML
    private Label Label3;

    @FXML
    private Label Label4;

    @FXML
    private Label Label5;

    @FXML
    private Label Label6;

    @FXML
    private Label Label7;

    @FXML
    void B1Pressed(ActionEvent event) {
        System.out.println("B1");
    }

    @FXML
    void B2Pressed(ActionEvent event) {
        System.out.println("B2");
    }

    @FXML
    void B3Pressed(ActionEvent event) {
        System.out.println("B3");
    }

    @FXML
    void B4Pressed(ActionEvent event) {
        System.out.println("B4");
    }

    @FXML
    void B5Pressed(ActionEvent event) {
        System.out.println("B5");
    }

    @FXML
    void B6Pressed(ActionEvent event) {
        System.out.println("B6");
    }

    @FXML
    void B7Pressed(ActionEvent event) {
        System.out.println("B7");
    }

}
