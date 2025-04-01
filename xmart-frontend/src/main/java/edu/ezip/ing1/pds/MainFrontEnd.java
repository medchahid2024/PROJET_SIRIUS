package edu.ezip.ing1.pds;

import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class MainFrontEnd extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFrontEnd.class.getResource("/Connexion2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainFrontEnd.class.getResource("/css.css").toExternalForm());

        stage.setTitle("Welcome to your account");

        stage.setScene(scene);
        stage.show();

    }

    private final static String LoggingLabel = "FrontEnd";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();

    public static void main(String[] args) throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        logger.debug("Load Network config file : {}", networkConfig.toString());

//        final stageService studentService = new stageService(networkConfig);
//
//
//        Stagess students = studentService.selectStages();
//        final AsciiTable asciiTable = new AsciiTable();
//        for (final Stagee student : students.getStages()) {
//            asciiTable.addRule();
//            asciiTable.addRow(student.getTitre(), student.getDescription(), student.getDomaine(),student.getDuree());
//        }
//        asciiTable.addRule();
//        logger.debug("\n{}\n", asciiTable.render());

        launch(args);
    }

}