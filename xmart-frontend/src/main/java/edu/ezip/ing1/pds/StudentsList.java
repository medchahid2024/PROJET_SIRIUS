package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsList {

    private final static String networkConfigFile = "network.yaml";


    @FXML
    public TableColumn <Etudiant,String> Nom;

    @FXML
    public TableColumn <Etudiant,String> Prenom;

  @FXML
   public TableColumn <Etudiant, Integer> Nombre;

@FXML
public TableView<Etudiant> table ;
    public TableColumn <Etudiant,String> Matricule;
    public TableColumn <Etudiant,String> id;
    public TableColumn <Etudiant,Integer> detail;

    @FXML
    public void initialize () throws IOException, InterruptedException {

        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);

        Etudiants etudiants = stageService.selectAllEtudiant();
        ObservableList<Etudiant> observableList = FXCollections.observableArrayList(etudiants.getEtudiants());
        table.setItems(observableList);

        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        detail.setCellValueFactory(new PropertyValueFactory<>("detail"));


        detail.setCellFactory(col -> new TableCell<Etudiant, Integer>() {
            private final Button btn = new Button("Visualiser");

            {
                btn.setOnAction(event -> {
                    Etudiant item = getTableView().getItems().get(getIndex());

                    System.out.println("Afficher les détails de : "+item.getDetail()+"\n" + "\n" + item.getNom()+ "\n" +item.getPrenom());
                    System.out.println("Afficher les candidatures  : "+item.getTitre()+"\n" + "\n" + item.getDuree()+ "\n" +item.getDomaine());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/DonneeEtudiant.fxml"));
                    Stage stage = new Stage();
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setScene(scene);
                    DonneeEtudiant controller = loader.getController();
                    controller.setEtudiant(item);


                    stage.show();
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null); // Si la cellule est vide ou que l'item est null, on n'affiche rien
                } else {
                    setGraphic(btn);  // Sinon, on affiche le bouton "detail"
                }
            }
        });


    }

    }
