package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Stagess {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stages")
    private Set<Stagee> stagees = new LinkedHashSet<Stagee>();

    public  Stagess(){

    }
    public Set<Stagee> getStages() {
        return stagees;
    }

    public void setStages(Set<Stagee> stagees) {
        this.stagees = stagees;
    }

    public final Stagess add (final Stagee stagee) {


        stagees.add(stagee);
        return this;
    }
}
