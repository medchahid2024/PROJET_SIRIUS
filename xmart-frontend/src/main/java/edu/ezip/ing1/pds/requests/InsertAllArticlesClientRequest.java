package edu.ezip.ing1.pds.requests;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ezip.ing1.pds.business.dto.Article;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class InsertAllArticlesClientRequest extends ClientRequest<Article, String> {

    public InsertAllArticlesClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Article info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> articleIdMap = mapper.readValue(body, Map.class);
        final String result  = articleIdMap.get("article_id");
        return result;
    }
}

