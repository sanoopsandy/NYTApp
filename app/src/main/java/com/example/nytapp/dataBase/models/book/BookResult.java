package com.example.nytapp.dataBase.models.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookResult {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contributor")
    @Expose
    private String contributor;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("publisher")
    @Expose
    private String publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
