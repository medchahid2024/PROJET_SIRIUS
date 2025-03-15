package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.Update;
import edu.ezip.ing1.pds.services.stageService;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemandeConnexion {
    public Label nom;
    public Label email;
    public Label matricule;
    public Label prenom;
    private final static String networkConfigFile = "network.yaml";
    public ImageView photo;
    private List<Etudiant> stageList = new ArrayList<>();
    private int currentIndex = 0;
    int matr;

    public void initialize() {
        try {
            loadStageData();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadStageData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);

        Etudiants etudiants = stageService.selectEtudiant();

        if (etudiants != null) {
            stageList = new ArrayList<>(etudiants.getEtudiants());
            currentIndex = 0;
            afficherStage(currentIndex);
        }
    }
    private void afficherStage(int index) {


        Etudiant etudiant = stageList.get(index);

        nom.setText(etudiant.getNom());
        prenom.setText(etudiant.getPrenom());
        matricule.setText(etudiant.getMatricule());
        email.setText(etudiant.getEmail());
        photo.setImage(new Image("file:" +"C:\\Users\\OMEN\\Desktop\\prototype-ing1\\xmart-frontend\\src\\main\\resources\\photo"+"/"+ etudiant.getPhoto()));
        System.out.println("Nom: " + etudiant.getNom());
        System.out.println("Prénom: " + etudiant.getPrenom());
        System.out.println("Matricule: " + etudiant.getMatricule());
        System.out.println("Email: " + etudiant.getEmail());
        System.out.println("Photo: " + etudiant.getPhoto());


    }

    public void suivant(MouseEvent mouseEvent) {
        if (currentIndex < stageList.size() - 1) {
            currentIndex++;
            afficherStage(currentIndex);
        }
    }

    public void precedent(MouseEvent mouseEvent) {
        if (currentIndex > 0) {
            currentIndex--;
            afficherStage(currentIndex);
        }
    }

    public void accepter(MouseEvent mouseEvent) throws SQLException, IOException, InterruptedException {
        Etudiant etudiant = stageList.get(currentIndex);
         matr=etudiant.getId();
        Update.update("UPDATE_ETUDIANT",matr);
        System.out.println("Nom: " + matr);
    }
}

