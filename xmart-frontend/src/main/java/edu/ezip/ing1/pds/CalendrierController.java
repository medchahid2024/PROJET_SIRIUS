package edu.ezip.ing1.pds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.EvenementService;
import edu.ezip.ing1.pds.services.InsertParticipation;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Evenement;
import edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.business.dto.Participations;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;

import edu.ezip.ing1.pds.services.EvenementService;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.InsertParticipation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalendrierController {

    private final static String networkConfigFile = "network.yaml";
    private List<Evenement> evenementList = new ArrayList<>();
    private int Index = 0;

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

    private Evenement evenement;



    @FXML
    void B1Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(0);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
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
    void B1Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 1;
        DemarrageParticiper(event);

    }

    @FXML
    void B2Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(1);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
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
    void B2Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 2;
        DemarrageParticiper(event);
    }

    @FXML
    void B3Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(2);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
        Domaine.setVisible(true);
    }

    @FXML
    void B3Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B3Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 3;
        DemarrageParticiper(event);
    }

    @FXML
    void B4Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(3);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
        Domaine.setVisible(true);
    }

    @FXML
    void B4Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B4Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 4;
        DemarrageParticiper(event);
    }

    @FXML
    void B5Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(4);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
        Domaine.setVisible(true);
    }

    @FXML
    void B5Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B5Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 5;
        DemarrageParticiper(event);
    }

    @FXML
    void B6Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(5);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
        Domaine.setVisible(true);
    }

    @FXML
    void B6Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B6Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 6;
        DemarrageParticiper(event);
    }

    @FXML
    void B7Ent(MouseEvent event) {
        LabelSelec.setVisible(false);

        evenement = evenementList.get(6);
        
        Titre.setText(evenement.getTitre());
        Titre.setVisible(true);
        
        Heure.setText(evenement.getHeure());
        Heure.setVisible(true);

        Description.setText(evenement.getDescription());
        Description.setVisible(true);

        Domaine.setText(evenement.getDomaine());
        Domaine.setVisible(true);
    }

    @FXML
    void B7Exi(MouseEvent event) {
        Titre.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Domaine.setVisible(false);
        LabelSelec.setVisible(true);
    }

    @FXML
    void B7Pressed(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, InterruptedException {
        Index = 7;
        DemarrageParticiper(event);
    }

    public void InitialisationCalendrier() throws IOException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        loadEvenementData();

        for (int i = 0; i < evenementList.size(); i++) {
            if (i < 7) { 
                Evenement evenement = evenementList.get(i);
                Label label = (Label) getClass().getDeclaredField("Label" + (i + 1)).get(this);
                Label heure = (Label) getClass().getDeclaredField("Heure" + (i + 1)).get(this);
    
                
                label.setText(evenement.getTitre());
                heure.setText(evenement.getHeure());
            }
        }
        
}

    private void loadEvenementData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final EvenementService evenementService = new EvenementService(networkConfig);

        Evenements evenements = evenementService.selectEvenements();

        if (evenements != null) {
            evenementList = new ArrayList<>(evenements.getEvenements());

            Collections.sort(evenementList, new Comparator<Evenement>() {
                @Override
                public int compare(Evenement e1, Evenement e2) {
                    return Integer.compare(e1.getId(), e2.getId());
                }
            });
    }

    }

    public void DemarrageParticiper(ActionEvent event) throws IOException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Participer.fxml"));
    Stage stage = new Stage();

    Scene par = new Scene(fxmlLoader.load());
    stage.setScene(par);
    
    stage.setTitle("Participer");

    // Récupérer le contrôleur de la vue Calendrier
    ParticiperController participerController = fxmlLoader.getController();

    // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
    participerController.InitialisationParticipation(Index);
    
    stage.show();
}
}

