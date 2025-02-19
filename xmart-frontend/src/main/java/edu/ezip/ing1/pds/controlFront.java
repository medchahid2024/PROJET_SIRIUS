package edu.ezip.ing1.pds;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;


public class controlFront {
  @FXML
  public Label label1;

  @FXML
  public Button ok;
  public Label label;


  public ImageView image;
  public Label clique;
    public ImageView offre_stage;
  public AnchorPane parent;

  public void clique(MouseEvent mouseEvent) throws IOException {

      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/control_stage.fxml"));
      Stage stage = new Stage();

      Scene offre = new Scene(fxmlLoader.load(), 700, 700);
      stage.setScene(offre);
      stage.setTitle("offres de stages");
      stage.show();


  }

    public void offre_stage(MouseEvent mouseEvent) throws IOException {
    clique(mouseEvent);
    }

    public static class ControlAdminStage {
    }
}
