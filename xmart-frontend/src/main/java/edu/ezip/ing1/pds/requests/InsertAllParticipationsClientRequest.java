package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Participation;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class InsertAllParticipationsClientRequest extends ClientRequest<Participation, String> {

    public InsertAllParticipationsClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Participation info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> participationIdMap = mapper.readValue(body, Map.class);
        final String result  = participationIdMap.get("participation_id");
        return result;
    }
}

