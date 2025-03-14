package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Evenements {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("evenements")
    private Set<Evenement> evenements = new LinkedHashSet<Evenement>();

    public  Evenements(){

    }
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    public final Evenements add (final Evenement evenement) {


        evenements.add(evenement);
        return this;
    }
}
