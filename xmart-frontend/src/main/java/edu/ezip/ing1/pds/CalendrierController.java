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
import javafx.scene.input.MouseDragEvent;
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
    private Label Description;

    @FXML
    private Label Domaine;

    @FXML
    private Label Heure;

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
    private Label LabelSelec;

    @FXML
    private Label Titre;



    @FXML
    void B1Ent(MouseEvent event) {
        LabelSelec.setVisible(false);
        Titre.setText("Toto1");
        Titre.setVisible(true);
        Heure.setVisible(true);
        Description.setVisible(true);
        Domaine.setVisible(true);
    }

    @FXML
    void B1Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B1Pressed(ActionEvent event) {

    }

    @FXML
    void B2Ent(MouseEvent event) {
        LabelSelec.setVisible(false);
        Titre.setText("Toto2");
        Titre.setVisible(true);
        Heure.setVisible(true);
        Description.setVisible(true);
        Domaine.setVisible(true);
    }

    @FXML
    void B2Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B2Pressed(ActionEvent event) {

    }

    @FXML
    void B3Ent(MouseEvent event) {

    }

    @FXML
    void B3Exi(MouseEvent event) {

    }

    @FXML
    void B3Pressed(ActionEvent event) {

    }

    @FXML
    void B4Ent(MouseEvent event) {

    }

    @FXML
    void B4Exi(MouseEvent event) {

    }

    @FXML
    void B4Pressed(ActionEvent event) {

    }

    @FXML
    void B5Ent(MouseEvent event) {

    }

    @FXML
    void B5Exi(MouseEvent event) {

    }

    @FXML
    void B5Pressed(ActionEvent event) {

    }

    @FXML
    void B6Pressed(ActionEvent event) {

    }

    @FXML
    void B7Pressed(ActionEvent event) {

    }

}
