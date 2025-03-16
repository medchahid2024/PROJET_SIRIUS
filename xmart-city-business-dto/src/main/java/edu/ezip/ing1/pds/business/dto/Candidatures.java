package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Candidatures {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stages")
    private Set<Candidature> Candidatures = new LinkedHashSet<Candidature>();
    private List<Candidature> candidaturesList;

    public  Candidatures(){

    }
    public Set<Candidature> getCandidatures() {
        return Candidatures;
    }

    public void setCandidatures(Set<Candidature> Candidatures) {
        this.Candidatures = Candidatures;
    }



    public final Candidatures add (final Candidature Candidature) {


        Candidatures.add(Candidature);
        return this;
    }
}
