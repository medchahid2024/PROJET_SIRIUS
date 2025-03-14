package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.commons.LoggingUtils;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Candidatures;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
//import edu.ezip.ing1.pds.requests.InsertStudentsClientRequest;
//import edu.ezip.ing1.pds.requests.InsertAllCandidaturesClientRequest;
//import edu.ezip.ing1.pds.requests.InsertAllCandidaturesClientRequest;
import edu.ezip.ing1.pds.requests.SelectAllStagesClientRequest;
//import edu.ezip.ing1.pds.requests.SelectAllStudentsClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class stageService {
    private final static String LoggingLabel = "FrontEnd - StageService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);


   final String insertRequestOrder = "INSERT_CANDIDATURE";
    final String selectRequestOrder = "SELECT_STAGE";
    final String selectRequest = "SELECT_OFFRE";




    private final NetworkConfig networkConfig;


    public stageService(NetworkConfig networkConfig) throws InterruptedException {
        this.networkConfig = networkConfig;
    }


//    public void insertCandidatures(Candidature candidature) throws InterruptedException, IOException {
//        final Deque<ClientRequest> clientRequests = new ArrayDeque<>();
//        final ObjectMapper objectMapper = new ObjectMapper();
//
//        final String requestId = UUID.randomUUID().toString();
//        final Request request = new Request();
//        request.setRequestId(requestId);
//        request.setRequestOrder(insertRequestOrder);
//
//        // Convertir l'objet `Candidature` en JSON pour l'envoyer dans la requête
//        final String candidatureJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(candidature);
//        request.setRequestContent(candidatureJson);
//
//        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//        final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
//
//        final InsertAllCandidaturesClientRequest clientRequest = new InsertAllCandidaturesClientRequest(
//                networkConfig, 0, request, null, requestBytes);
//        clientRequests.push(clientRequest);
//
//        while (!clientRequests.isEmpty()) {
//            final ClientRequest client = clientRequests.pop();
//            client.join();
//
//            Candidature response = (Candidature) clientRequest.getInfo();
//            if (response != null) {
//                logger.debug("Thread {} complete : Nom: {}, Prénom: {}, Email: {}, Adresse: {}, CV: {}, Lettre: {}, Autres: {}, ID: {}",
//                        clientRequest.getThreadName(),
//                        response.getNom(), response.getPrenom(), response.getEmail(), response.getAdresse(),
//                        response.getCv(), response.getLettre(), response.getAutres());
//            } else {
//                logger.error("Erreur : Impossible de récupérer la candidature insérée.");
//            }
//        }
//    }


    public Stagess selectStages() throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(selectRequestOrder);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectAllStagesClientRequest clientRequest = new SelectAllStagesClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Stagess) joinedClientRequest.getResult();
        }
        else {
            logger.error("No stages found");
            return null;
        }
    }
    public Stagess selectOffres(String recherche) throws InterruptedException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(selectRequest);
        request.setRequestContent(recherche);

        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

        // Log uniquement si le niveau de log est TRACE
        if (logger.isTraceEnabled()) {
            LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        }

        // Création de la requête client
        final SelectAllStagesClientRequest clientRequest = new SelectAllStagesClientRequest(
                networkConfig,
                0, request, null, requestBytes);

        // Traitement de la requête
        clientRequest.join(); // Directement on attend l'exécution de la requête
        logger.debug("Thread {} complete.", clientRequest.getThreadName());

        // Vérification du résultat et gestion des erreurs de type
        Object result = clientRequest.getResult();
        if (result instanceof Stagess) {
            return (Stagess) result;
        } else {
            logger.error("Le résultat obtenu n'est pas un Stagess.");
            return null;
        }
    }


}

