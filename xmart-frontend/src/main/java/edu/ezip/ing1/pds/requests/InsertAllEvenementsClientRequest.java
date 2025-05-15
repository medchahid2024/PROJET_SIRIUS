package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Evenement;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class InsertAllEvenementsClientRequest extends ClientRequest<Evenement, String> {

    public InsertAllEvenementsClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Evenement info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> evenementIdMap = mapper.readValue(body, Map.class);
        final String result  = evenementIdMap.get("evenement_id");
        return result;
    }
}

