package edu.ezip.ing1.pds;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class connexion {
    public PasswordField motdepasse;
    public Button seConnecter;
    public TextField email;

    public void seConnecter(ActionEvent actionEvent) throws IOException {
        if (email.getText().equals("admin")&&motdepasse.getText().equals("admin")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        else if (email.getText().equals("")||motdepasse.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'email ou le mot de passe est incorrect");
            alert.showAndWait();
        }
    }

    public void CreateAcount(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAcount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
