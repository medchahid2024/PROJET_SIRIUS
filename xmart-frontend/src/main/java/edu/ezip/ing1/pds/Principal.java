package edu.ezip.ing1.pds;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Principal {
  @FXML
  public Label label1;

  @FXML
  public Button ok;
  public Label label;


  public ImageView image;
  public Label clique;
    public ImageView offre_stage;
  public AnchorPane parent;


      @FXML
    private Button BClose;

    @FXML
    private Button BOpen;

    @FXML
    private ImageView Image;

    @FXML
    void MEnt(MouseEvent event) {
      TranslateTransition translate = new TranslateTransition();
      translate.setNode(Image);
      translate.setByX(238);
      translate.play();
      
    }

   
    @FXML
    void MExi(MouseEvent event) {
      TranslateTransition translate = new TranslateTransition();
      translate.setNode(Image);
      translate.setByX(-238);
      translate.play();
    }

 


  public void clique(MouseEvent mouseEvent) throws IOException {

      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/control_stage.fxml"));
      Stage stage = new Stage();

      Scene offre = new Scene(fxmlLoader.load());
      stage.setScene(offre);
      stage.setTitle("offres de stages");
      stage.show();


  }

  public void DemarrageCalendrier(MouseEvent mouseEvent) throws IOException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Calendrier.fxml"));
    Stage stage = new Stage();

    Scene calendar = new Scene(fxmlLoader.load());
    stage.setScene(calendar);
    stage.setTitle("Calendrier Collaboratif");
    // Récupérer le contrôleur de la vue Calendrier
    CalendrierController calendrierController = fxmlLoader.getController();

    // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
    calendrierController.InitialisationCalendrier();
    
    stage.show();
   


}

    public void offre_stage(MouseEvent mouseEvent) throws IOException {
    clique(mouseEvent);
    }

    public void evenement(MouseEvent mouseEvent) throws IOException, InvocationTargetException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    DemarrageCalendrier(mouseEvent);
    }

    public void commerce(MouseEvent mouseEvent) throws InterruptedException, IOException {
    Fenetre1 f=new Fenetre1();
    f.setVisible(true);
    }

    public static class ControlAdminStage {
    }
}
