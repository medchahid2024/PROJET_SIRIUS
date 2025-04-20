package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

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

    }
}
