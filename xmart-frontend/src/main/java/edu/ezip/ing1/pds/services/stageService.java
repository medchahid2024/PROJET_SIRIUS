package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.commons.LoggingUtils;

import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
//import edu.ezip.ing1.pds.requests.InsertStudentsClientRequest;
//import edu.ezip.ing1.pds.requests.InsertAllCandidaturesClientRequest;
//import edu.ezip.ing1.pds.requests.InsertAllCandidaturesClientRequest;
import edu.ezip.ing1.pds.requests.SelectAllStagesClientRequest;
//import edu.ezip.ing1.pds.requests.SelectAllStudentsClientRequest;
import edu.ezip.ing1.pds.requests.SelectCountCandidatureClientRequest;
import edu.ezip.ing1.pds.requests.SelectEtudiantClientRequest;
import edu.ezip.ing1.pds.requests.UpdateStageClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class stageService {
    private final static String LoggingLabel = "FrontEnd - StageService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);


    final String selectRequestOrder = "SELECT_STAGE";
    final String selectRequest = "SELECT_OFFRE";
    final String selectetudiant = "SELECT_ETUDIANT";
    final String selectAlletudiant = "SELECT_ALL_STUDENTS";



    private final static String networkConfigFile = "network.yaml";
    private final NetworkConfig networkConfig;


    public stageService(NetworkConfig networkConfig) throws InterruptedException {
        this.networkConfig = networkConfig;
    }



    public Stagess selectStages() throws InterruptedException, IOException {
        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
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
        request.setSearchKeyword(recherche);

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
    public Etudiants selectAllEtudiant() throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(selectAlletudiant);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectEtudiantClientRequest clientRequest = new SelectEtudiantClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Etudiants) joinedClientRequest.getResult();
        }
        else {
            logger.error("No student found");
            return null;
        }
    }
    public Etudiants selectEtudiant() throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(selectetudiant);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectEtudiantClientRequest clientRequest = new SelectEtudiantClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Etudiants) joinedClientRequest.getResult();
        }
        else {
            logger.error("No student found");
            return null;
        }
    }


    public Etudiants selectConnexion (String requestOrder, Object object) throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
       request.setRequestContent(objectMapper.writeValueAsString(object));

        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectEtudiantClientRequest clientRequest = new SelectEtudiantClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Etudiants) joinedClientRequest.getResult();
        }
        else {
            logger.error("No student found");
            return null;
        }
    }
    public Candidatures selectCountCandidature(String requestOrder, Object object) throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(String.valueOf((int) object));
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectCountCandidatureClientRequest clientRequest = new SelectCountCandidatureClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Candidatures) joinedClientRequest.getResult();
        }
        else {
            logger.error("No student found");
            return null;
        }
    }
    public Stagess SelectEtudiantCandidature (String requestOrder, Object object) throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(objectMapper.writeValueAsString(object));

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
            logger.error("No student found");
            return null;
        }
    }

}

