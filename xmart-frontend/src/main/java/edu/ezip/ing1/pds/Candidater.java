package edu.ezip.ing1.pds;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Candidater  implements Initializable {

    public Button motivation;
    public Button CV;
    public Button autres;
    public Label lab;
    public Label lett;
    public Label autre;
    FileChooser fileChooser = new FileChooser();


    public void autres(MouseEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            autre.setText(file.getName());
        } else {
            autre.setText("Aucun fichier sélectionné");
        }

    }

    public void lettre(MouseEvent mouseEvent) {
        File file1 = fileChooser.showOpenDialog(null);
        if (file1 != null) {
            lett.setText(file1.getName());
        } else {
            lett.setText("Aucun fichier sélectionné");
        }
    }

    public void CV(MouseEvent mouseEvent) {
        File file2 = fileChooser.showOpenDialog(null);
        if (file2 != null) {
            lab.setText(file2.getName());
        } else {
            lab.setText("Aucun fichier sélectionné");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void valider(ActionEvent actionEvent) {
    }
}
