package edu.ezip.ing1.pds;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin {
    public void mesDemandes(MouseEvent mouseEvent) throws IOException {
        System.out.println("heemlooo");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MesDemandes.fxml"));
        Stage stage = new Stage();

        Scene demande = new Scene(fxmlLoader.load());
        stage.setScene(demande);
        stage.setTitle("Mes demandes");
        stage.show();
    }
}
