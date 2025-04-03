package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Stagee;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Modifyoffre {
    public TextField duree;
    public Button modifier;
    public TextArea description;
    public TextField domaine;
    public TextField niveau;
    public TextField titre;

public void setInformation(Stagee stage) {

    duree.setText(stage.getDuree());

    description.setText(stage.getDescription());
    domaine.setText(stage.getDomaine());
    niveau.setText(stage.getNiveau());
    titre.setText(stage.getTitre());

    }

    public void Modifier(ActionEvent actionEvent) {

    }
}
