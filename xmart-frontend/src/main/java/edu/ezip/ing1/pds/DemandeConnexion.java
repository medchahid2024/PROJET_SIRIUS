package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemandeConnexion {
    public Label nom;
    public Label email;
    public Label matricule;
    public Label prenom;
    private final static String networkConfigFile = "network.yaml";
    private List<Etudiant> stageList = new ArrayList<>();
    private int currentIndex = 0;

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
}
