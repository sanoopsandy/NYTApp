package com.example.nytapp.dataBase.models.topStory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopStory {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<TopStoryResult> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<TopStoryResult> getResults() {
        return results;
    }

    public void setResults(List<TopStoryResult> results) {
        this.results = results;
    }

}
