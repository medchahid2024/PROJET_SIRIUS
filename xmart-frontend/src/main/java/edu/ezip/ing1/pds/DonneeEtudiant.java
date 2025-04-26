package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DonneeEtudiant {
    public Label nom;
    public Label email;
    public Label prenom;
    public ImageView photo;
    public Label matricule;
    public Label Derniere_conn;
    public TableView <Etudiant>table;
    public TableColumn <Etudiant,String> titre;
    public TableColumn <Etudiant,String>domaine;
    public TableColumn <Etudiant,String>duree;

    public void setEtudiant(Etudiant etudiant){
        nom.setText(etudiant.getNom());
        email.setText(etudiant.getEmail());
        prenom.setText(etudiant.getPrenom());
        photo.setImage(new Image((getClass().getResource("/photo/" + etudiant.getPhoto()).toExternalForm())));

        matricule.setText(etudiant.getMatricule());
        Derniere_conn.setText(etudiant.getDate().toString());

        Etudiants etudiants = new Etudiants();


        ObservableList <Etudiant> observableList = FXCollections.observableArrayList(etudiants.getEtudiants());
        observableList.add(etudiant);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        table.setItems(observableList);


    }

}
