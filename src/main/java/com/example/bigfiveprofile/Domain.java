package com.example.bigfiveprofile;

import java.util.Dictionary;
import java.util.Hashtable;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Domain implements Serializable{

    @JsonDeserialize(using = DomainDeserializer.class)

    private String domainName;
    private Integer overallScore;
    private Dictionary<String, Integer> facets;
    public Domain(String domainName, Integer overallScore) {
        this.domainName = domainName;
        this.overallScore = overallScore;
        this.facets = new Hashtable<String, Integer>();
    }
    //TODO adding Facets SHOULD recompute overallScore
    public void addFacet(String facetTitle, Integer score) {
        this.facets.put(facetTitle, score);
    }
    public void addFacet(String facetTitle) {
        this.facets.put(facetTitle, 0);
    }
    //TODO a pair return would have been more concise API
    //public Pair<String, Integer> getDomain() { return new Pair(this.domainName, this.overallScore); }
    public String getDomainName() { return this.domainName; }
    public Integer getOverallScore() { return this.overallScore; }
    public Dictionary<String, Integer> getFacets() { return this.facets; }
    public void updateOverallScore(Integer overallScore) { this.overallScore = overallScore; }
    @Override
    public String toString() {
        String representation = this.domainName + "\nOverall Score: " + this.overallScore;
        return representation;
    }

}
