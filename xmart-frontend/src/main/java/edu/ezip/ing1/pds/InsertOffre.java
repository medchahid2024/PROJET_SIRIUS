package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.Update;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class InsertOffre {
    public TextField titre;
    public TextField niveau;
    public TextField domaine;
    public TextArea description;
    public TextField duree;
    String tit;
    String nive;
    String doma;
    String desc;
    String dure;

    public void insererOffre() {
        tit=titre.getText();
        nive=niveau.getText();
       doma=domaine.getText();
        desc=description.getText();
        dure=duree.getText();
    }


    public void valider(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {
        boolean b = domaine.equals("") || description.equals("");
        if (titre.getText().equals("") || niveau.getText().equals("") ||domaine.equals("") || description.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        else {
            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("CANFIRMATION");
            aler.setHeaderText(null);
            aler.setContentText("Vous voulez vraiment ajouter une offre");
            Optional<ButtonType> result = aler.showAndWait();
            if (result.get() == ButtonType.OK) {
                insererOffre();
                Stagee stagee = new Stagee(tit, nive, doma, desc, dure);
                InsertCandidature.sendStage("INSERT_STAGE", stagee);
                System.out.println(tit);
                System.out.println(nive);
                System.out.println(doma);
                System.out.println(desc);
                System.out.println(dure);
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

            }


        }

    }
}
