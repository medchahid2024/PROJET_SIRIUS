package edu.ezip.ing1.pds;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAcount {
    public void connexion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/connexion.fxml"));
        Stage stage = new Stage();
        Scene scene= new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void kk(ActionEvent actionEvent) {
        
    }
}
