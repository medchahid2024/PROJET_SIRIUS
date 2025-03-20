package edu.ezip.ing1.pds;

import java.io.IOException;
import java.sql.SQLException;

import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.services.InsertParticipation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    void BoutonInscrire(ActionEvent event) throws IOException, SQLException, InterruptedException {

        String nom = TFNom.getText();
        String prenom = TFPrenom.getText();
        String email = TFEmail.getText();
        
        this.i = i;

        if (prenom == null || prenom.isEmpty() || nom == null || nom.isEmpty() ||
                email == null || email.isEmpty() ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement.");
            alert.showAndWait();
}

else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { // Vérification améliorée

    Alert alert2 = new Alert(Alert.AlertType.ERROR);
    alert2.setTitle("Erreur");
    alert2.setHeaderText(null);
    alert2.setContentText("Le format de l'adresse email est incorrect.");
    alert2.showAndWait();

}

else if (!nom.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ-]+$")) { // Vérifie que le nom ne contient que des lettres

    Alert alert5 = new Alert(Alert.AlertType.ERROR);
    alert5.setTitle("Erreur");
    alert5.setHeaderText(null);
    alert5.setContentText("Le nom ne doit contenir que des lettres.");
    alert5.showAndWait();

} else if (!prenom.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ-]+$")) { // Vérifie que le prénom ne contient que des lettres

    Alert alert6 = new Alert(Alert.AlertType.ERROR);
    alert6.setTitle("Erreur");
    alert6.setHeaderText(null);
    alert6.setContentText("Le prénom ne doit contenir que des lettres.");
    alert6.showAndWait();

}

        else {
            Participation parti = new Participation(nom,prenom,email,i);
            InsertParticipation.sendValue("INSERT_PARTICIPATION", parti);
            
            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("Validé");
            aler.setHeaderText(null);
            aler.setContentText("Inscription réussie !");
            aler.showAndWait();

        }

        
    }

    public void InitialisationParticipation(int i) {
        this.i = i;
    }

}
