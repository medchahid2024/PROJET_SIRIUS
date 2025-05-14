package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DonneeEtudiant {
    public Label nom;
    public Label email;
    public Label prenom;
    public ImageView photo;
    public Label matricule;
    public Label Derniere_conn;
    public TableView <Stagee>table;
    public TableColumn <Stagee,String> titre;
    public TableColumn <Stagee,String>domaine;
    public TableColumn <Stagee,String>duree;
    private final static String networkConfigFile = "network.yaml";


    public void setEtudiant(Etudiant etudiant) throws InterruptedException, SQLException, IOException {
        nom.setText(etudiant.getNom());
        email.setText(etudiant.getEmail());
        prenom.setText(etudiant.getPrenom());
        photo.setImage(new Image((getClass().getResource("/photo/" + etudiant.getPhoto()).toExternalForm())));
        int id= etudiant.getDetail();
        int idoffre= etudiant.getId_offre();
        System.out.println(etudiant.getDetail());

        matricule.setText(etudiant.getMatricule());
        Derniere_conn.setText(etudiant.getDate().toString());
     Stagee stagee =new Stagee(id,idoffre);

        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);
        Stagess stagess = stageService.SelectEtudiantCandidature("SELECT_CANDIDATURES_ETUDIANT", stagee);



        ObservableList <Stagee> observableList = FXCollections.observableArrayList(stagess.getStages());
        System.out.println("Titre: " + stagee.getTitre() + ", Domaine: " + stagee.getDomaine() + ", Durée: " + stagee.getDuree());
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        table.setItems(observableList);


    }

}
