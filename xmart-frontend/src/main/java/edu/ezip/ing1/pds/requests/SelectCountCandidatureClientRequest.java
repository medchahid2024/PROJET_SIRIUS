package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;


import edu.ezip.ing1.pds.business.dto.Candidatures;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectCountCandidatureClientRequest extends ClientRequest<Object, Candidatures> {

    public SelectCountCandidatureClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Candidatures readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Candidatures candidatures = mapper.readValue(body, Candidatures.class);
        return candidatures;
    }
}
