package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Participations;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllParticipationsClientRequest extends ClientRequest<Object, Participations> {

    public SelectAllParticipationsClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Participations readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Participations participations = mapper.readValue(body, Participations.class);
        return participations;
    }
}
