package edu.ezip.ing1.pds;

import java.io.IOException;
import java.sql.SQLException;

import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.services.InsertParticipation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ParticiperController {

    @FXML
    private Button Inscription;

    @FXML
    private TextField TFEmail;

    @FXML
    private TextField TFNom;

    @FXML
    private TextField TFPrenom;

    private int i;

    @FXML
    void BoutonInscrire (ActionEvent event) throws IOException, SQLException, InterruptedException {

        String nom = TFNom.getText();
        String prenom = TFPrenom.getText();
        String email = TFEmail.getText();
        
        this.i = i;

        Participation parti = new Participation(nom,prenom,email,i);
        InsertParticipation.sendValue("INSERT_PARTICIPATION", parti);

    }

    public void InitialisationParticipation(int i) {
        this.i = i;
    }

}
