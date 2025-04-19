package edu.ezip.ing1.pds.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import edu.ezip.ing1.pds.requests.InsertAllCandidaturesClientRequest;
import edu.ezip.ing1.pds.requests.InsertAllEtudiantsClientRequest;
import edu.ezip.ing1.pds.requests.InsertAllStagesClientRequest;
import edu.ezip.ing1.pds.requests.InsertDeposerClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class InsertCandidature {
    private final static String LoggingLabel = "Insertion";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static void sendStage(String requestOrder, Object object) throws IOException, SQLException, InterruptedException {

        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

        logger.trace("Clients loaded : {}", object.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        logger.trace("Candidat to be inserted : {}", jsonifiedGuy);
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(jsonifiedGuy);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);


        final InsertAllStagesClientRequest etudiantsClientRequest = new InsertAllStagesClientRequest (
                networkConfig,
                birthdate++, request, null, requestBytes);
        etudiantsClientRequest.join();
    }

    public static void sendValeur(String requestOrder, Object object) throws IOException, SQLException, InterruptedException {

        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

        logger.trace("Clients loaded : {}", object.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        logger.trace("Candidat to be inserted : {}", jsonifiedGuy);
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(jsonifiedGuy);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);


        final InsertAllEtudiantsClientRequest etudiantsClientRequest = new InsertAllEtudiantsClientRequest (
                networkConfig,
                birthdate++, request, null, requestBytes);
        etudiantsClientRequest.join();
    }

    public static void sendDeposer(String requestOrder, Object object) throws IOException, SQLException, InterruptedException {

        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

        logger.trace("Clients loaded : {}", object.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        logger.trace("Candidat to be inserted : {}", jsonifiedGuy);
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(jsonifiedGuy);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);


        final InsertDeposerClientRequest etudiantsClientRequest = new InsertDeposerClientRequest (
                networkConfig,
                birthdate++, request, null, requestBytes);
        etudiantsClientRequest.join();
    }
    public static void sendValue(String requestOrder, Object object) throws IOException, SQLException, InterruptedException {

        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

        logger.trace("Clients loaded : {}", object.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        logger.trace("Candidat to be inserted : {}", jsonifiedGuy);
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(jsonifiedGuy);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

        final InsertAllCandidaturesClientRequest insert = new InsertAllCandidaturesClientRequest (
                networkConfig,
                birthdate++, request, null, requestBytes);
        insert.join();
}}