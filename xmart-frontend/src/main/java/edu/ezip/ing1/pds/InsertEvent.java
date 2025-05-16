package edu.ezip.ing1.pds;

import java.io.IOException;
import java.sql.SQLException;

import edu.ezip.ing1.pds.business.dto.Evenement;
import edu.ezip.ing1.pds.services.InsertEvenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InsertEvent {

    @FXML
    private TextField Adresse;

    @FXML
    private TextArea Description;

    @FXML
    private Button Insertion;

    @FXML
    private TextField Titre;

    private String jour,heure;

    @FXML
    void NewEvenement(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String adresse = Adresse.getText();
        String description = Description.getText();
        String titre = Titre.getText();
       
            Evenement even = new Evenement(titre,description,"Publié par un Admin",heure,adresse,jour);
            InsertEvenement.sendValue("INSERT_EVENEMENT", even);
            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("Validé");
            aler.setHeaderText(null);
            aler.setContentText("Evenement inseré !");
            aler.showAndWait();

            
    }

    public void InitialisationEvenements(String jour, String heure) {
        this.jour = jour;
        this.heure = heure;
    }


}
